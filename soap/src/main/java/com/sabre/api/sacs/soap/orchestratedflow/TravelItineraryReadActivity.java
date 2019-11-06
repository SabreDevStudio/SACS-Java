package com.sabre.api.sacs.soap.orchestratedflow;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sabre.api.sacs.configuration.SacsConfiguration;
import com.sabre.api.sacs.contract.travelitinerary.TravelItineraryReadRQ;
import com.sabre.api.sacs.contract.travelitinerary.TravelItineraryReadRS;
import com.sabre.api.sacs.errors.ErrorHandlingSchedule;
import com.sabre.api.sacs.soap.common.GenericRequestWrapper;
import com.sabre.api.sacs.soap.pool.SessionPool;
import com.sabre.api.sacs.workflow.Activity;
import com.sabre.api.sacs.workflow.SharedContext;

/**
 * Activity class to be used in the workflow. It runs the TravelItineraryRead
 * request.
 */
@Controller
@Scope("prototype")
public class TravelItineraryReadActivity implements Activity {

    private static final Logger LOG = LogManager.getLogger(TravelItineraryReadActivity.class);

    @Autowired
    private GenericRequestWrapper<TravelItineraryReadRQ, TravelItineraryReadRS> tir;

    @Autowired
    private ErrorHandlingSchedule errorHandler;

    @Autowired
    private SacsConfiguration configuration;

    @Autowired
    private SessionPool sessionPool;

    @Override
    public Activity run(SharedContext context) {
        Marshaller marsh;
        try {
            marsh = JAXBContext.newInstance("com.sabre.api.sacs.contract.travelitinerary").createMarshaller();
            StringWriter sw = new StringWriter();
            TravelItineraryReadRQ request = getRequestBody(context.getResult("PNR").toString());
            tir.setRequest(request);
            tir.setLastInFlow(true);
            marsh.marshal(request, sw);
            context.putResult("TravelItineraryReadRQ", sw.toString());
            TravelItineraryReadRS result = tir.executeRequest(context);
            if (result.getApplicationResults() != null && result.getApplicationResults().getError() != null
                    && !result.getApplicationResults().getError().isEmpty()) {
                context.setFaulty(true);
                LOG.warn("Error found, adding context to ErrorHandler. ConversationID: " + context.getConversationId());
                errorHandler.addSystemFailure(context);
                sessionPool.returnToPool(context.getConversationId());
                return null;
            }
            sw = new StringWriter();
            marsh.marshal(result, sw);
            context.putResult("TravelItineraryReadRS", sw.toString());
        } catch (JAXBException e) {
            LOG.error("Error while marshalling the response.", e);
        } catch (InterruptedException e) {
            LOG.catching(e);
        }

        return null;
    }

    private TravelItineraryReadRQ getRequestBody(String pnr) {

        TravelItineraryReadRQ body = new TravelItineraryReadRQ();

        body.setVersion(configuration.getSoapProperty("TravelItineraryReadRQVersion"));

        TravelItineraryReadRQ.MessagingDetails details = new TravelItineraryReadRQ.MessagingDetails();
        TravelItineraryReadRQ.MessagingDetails.SubjectAreas subjectAreas = new TravelItineraryReadRQ.MessagingDetails.SubjectAreas();
        subjectAreas.getSubjectArea().add("PNR");
        details.setSubjectAreas(subjectAreas);
        body.setMessagingDetails(details);

        TravelItineraryReadRQ.UniqueID uid = new TravelItineraryReadRQ.UniqueID();
        uid.setID(pnr);
        body.setUniqueID(uid);

        return body;
    }

}
