package com.sabre.api.sacs.soap.orchestratedflow;

import java.io.StringWriter;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sabre.api.sacs.configuration.SacsConfiguration;
import com.sabre.api.sacs.contract.passengerdetails.PassengerDetailsRQ;
import com.sabre.api.sacs.contract.passengerdetails.PassengerDetailsRS;
import com.sabre.api.sacs.errors.ErrorHandlingSchedule;
import com.sabre.api.sacs.soap.common.GenericRequestWrapper;
import com.sabre.api.sacs.soap.pool.SessionPool;
import com.sabre.api.sacs.workflow.Activity;
import com.sabre.api.sacs.workflow.SharedContext;

/**
 * Activity class to be used in the workflow. It runs the PassengerDetails request. 
 */
@Controller
@Scope("prototype")
public class PassengerDetailsActivity implements Activity {

    private static final Logger LOG = LogManager.getLogger(PassengerDetailsActivity.class);
    
	@Autowired
	private GenericRequestWrapper<PassengerDetailsRQ, PassengerDetailsRS> pd;
	
	@Autowired
	private TravelItineraryReadActivity next;
	
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
			marsh = JAXBContext.newInstance("com.sabre.api.sacs.contract.passengerdetails").createMarshaller();
			StringWriter sw = new StringWriter();
			PassengerDetailsRQ request = getRequestBody();
			pd.setRequest(request);
			pd.setLastInFlow(false);
			marsh.marshal(request, sw);
			context.putResult("PassengerDetailsRQ", sw.toString());
			PassengerDetailsRS result = pd.executeRequest(context);
			if (result.getApplicationResults() != null && result.getApplicationResults().getError() != null && !result.getApplicationResults().getError().isEmpty()) {
			    context.setFaulty(true);
			    LOG.warn("Error found, adding context to ErrorHandler. ConversationID: " + context.getConversationId());
			    errorHandler.addSystemFailure(context);
			    sessionPool.returnToPool(context.getConversationId());
			    return null;
			}
			sw = new StringWriter();
			marsh.marshal(result, sw);
			context.putResult("PassengerDetailsRS", sw.toString());
			context.putResult("PNR", result.getTravelItineraryReadRS().getTravelItinerary().getItineraryRef().getID());
		} catch (JAXBException e) {
		    LOG.error("Error while marshalling the response.", e);
		} catch (InterruptedException e) {
            LOG.catching(e);
        }

		return next;
	}
	
    private PassengerDetailsRQ getRequestBody() {

        PassengerDetailsRQ request = new PassengerDetailsRQ();
        request.setIgnoreOnError(false);
        request.setHaltOnError(true);
        request.setVersion(configuration.getSoapProperty("PassengerDetailsRQVersion"));

        request.setPostProcessing(getPostProcessing());
        request.setSpecialReqDetails(getSpecialReqDetails());
        request.setTravelItineraryAddInfoRQ(getTravelItineraryAddInfoRQ());

        return request;

    }


    private PassengerDetailsRQ.PostProcessing getPostProcessing() {

        PassengerDetailsRQ.PostProcessing postProcessing = new PassengerDetailsRQ.PostProcessing();
        postProcessing.setRedisplayReservation(true);
        PassengerDetailsRQ.PostProcessing.EndTransactionRQ endTransactionRQ = new PassengerDetailsRQ.PostProcessing.EndTransactionRQ();
        PassengerDetailsRQ.PostProcessing.EndTransactionRQ.Source source = new PassengerDetailsRQ.PostProcessing.EndTransactionRQ.Source();
        PassengerDetailsRQ.PostProcessing.EndTransactionRQ.EndTransaction endTransaction = new PassengerDetailsRQ.PostProcessing.EndTransactionRQ.EndTransaction();
        source.setReceivedFrom("SACSTesting");
        endTransaction.setInd("true");
        endTransactionRQ.setSource(source);
        endTransactionRQ.setEndTransaction(endTransaction);

        postProcessing.setEndTransactionRQ(endTransactionRQ);

        return postProcessing;
    }

    private PassengerDetailsRQ.SpecialReqDetails getSpecialReqDetails() {

        PassengerDetailsRQ.SpecialReqDetails specialReqDetails = new PassengerDetailsRQ.SpecialReqDetails();

        specialReqDetails.setAddRemarkRQ(getAddRemarkRQ());

        PassengerDetailsRQ.SpecialReqDetails.SpecialServiceRQ specialServiceRQ = new PassengerDetailsRQ.SpecialReqDetails.SpecialServiceRQ();
        PassengerDetailsRQ.SpecialReqDetails.SpecialServiceRQ.SpecialServiceInfo specialServiceInfo = new PassengerDetailsRQ.SpecialReqDetails.SpecialServiceRQ.SpecialServiceInfo();
        PassengerDetailsRQ.SpecialReqDetails.SpecialServiceRQ.SpecialServiceInfo.SecureFlight secureFlight = new PassengerDetailsRQ.SpecialReqDetails.SpecialServiceRQ.SpecialServiceInfo.SecureFlight();
        secureFlight.setSegmentNumber("A");

        PassengerDetailsRQ.SpecialReqDetails.SpecialServiceRQ.SpecialServiceInfo.SecureFlight.PersonName personName = new PassengerDetailsRQ.SpecialReqDetails.SpecialServiceRQ.SpecialServiceInfo.SecureFlight.PersonName();
        personName.setGivenName("SACS");
        personName.setSurname("TEST");
        personName.setDateOfBirth("1977-11-27");
        personName.setGender("M");
        personName.setNameNumber("1.1");
        PassengerDetailsRQ.SpecialReqDetails.SpecialServiceRQ.SpecialServiceInfo.SecureFlight.VendorPrefs vendorPrefs1 = new PassengerDetailsRQ.SpecialReqDetails.SpecialServiceRQ.SpecialServiceInfo.SecureFlight.VendorPrefs();
        PassengerDetailsRQ.SpecialReqDetails.SpecialServiceRQ.SpecialServiceInfo.SecureFlight.VendorPrefs.Airline airline1 = new PassengerDetailsRQ.SpecialReqDetails.SpecialServiceRQ.SpecialServiceInfo.SecureFlight.VendorPrefs.Airline();
        airline1.setHosted(true);
        vendorPrefs1.setAirline(airline1);
        secureFlight.setPersonName(personName);
        secureFlight.setVendorPrefs(vendorPrefs1);
        specialServiceInfo.getSecureFlight().add(secureFlight);
        specialServiceRQ.setSpecialServiceInfo(specialServiceInfo);

        return specialReqDetails;
    }

    private PassengerDetailsRQ.SpecialReqDetails.AddRemarkRQ getAddRemarkRQ() {

        PassengerDetailsRQ.SpecialReqDetails.AddRemarkRQ addRemarkRQ = new PassengerDetailsRQ.SpecialReqDetails.AddRemarkRQ();

        PassengerDetailsRQ.SpecialReqDetails.AddRemarkRQ.RemarkInfo remarkInfo = new PassengerDetailsRQ.SpecialReqDetails.AddRemarkRQ.RemarkInfo();
        PassengerDetailsRQ.SpecialReqDetails.AddRemarkRQ.RemarkInfo.FOPRemark fopRemark = new PassengerDetailsRQ.SpecialReqDetails.AddRemarkRQ.RemarkInfo.FOPRemark();

        PassengerDetailsRQ.SpecialReqDetails.AddRemarkRQ.RemarkInfo.Remark[] remarks = new PassengerDetailsRQ.SpecialReqDetails.AddRemarkRQ.RemarkInfo.Remark[12];

        String[] texts = { "TEST GENERAL REMARK 1",
                "TEST HIDDEN REMARK", "TEST HISTORICAL REMARK" };

        String[] types = { "General", "Hidden", "Historical" };

        for (int i = 0; i < texts.length; ++i) {
            remarks[i] = new PassengerDetailsRQ.SpecialReqDetails.AddRemarkRQ.RemarkInfo.Remark();
            remarks[i].setText(texts[i]);
            remarks[i].setType(types[i]);
        }

        fopRemark.setType("CASH");
        remarkInfo.getRemark().addAll(Arrays.asList(remarks));
        remarkInfo.setFOPRemark(fopRemark);
        addRemarkRQ.setRemarkInfo(remarkInfo);

        return addRemarkRQ;
    }

    private PassengerDetailsRQ.TravelItineraryAddInfoRQ getTravelItineraryAddInfoRQ() {

        PassengerDetailsRQ.TravelItineraryAddInfoRQ travelItineraryAddInfoRQ = new PassengerDetailsRQ.TravelItineraryAddInfoRQ();
        travelItineraryAddInfoRQ.setAgencyInfo(getAgencyInfo());

        return travelItineraryAddInfoRQ;
    }

    private PassengerDetailsRQ.TravelItineraryAddInfoRQ.AgencyInfo getAgencyInfo() {
        PassengerDetailsRQ.TravelItineraryAddInfoRQ.AgencyInfo agencyInfo = new PassengerDetailsRQ.TravelItineraryAddInfoRQ.AgencyInfo();
        PassengerDetailsRQ.TravelItineraryAddInfoRQ.AgencyInfo.Address address = new PassengerDetailsRQ.TravelItineraryAddInfoRQ.AgencyInfo.Address();
        PassengerDetailsRQ.TravelItineraryAddInfoRQ.AgencyInfo.Ticketing ticketing = new PassengerDetailsRQ.TravelItineraryAddInfoRQ.AgencyInfo.Ticketing();
        PassengerDetailsRQ.TravelItineraryAddInfoRQ.AgencyInfo.Address.StateCountyProv stateCountyProv = new PassengerDetailsRQ.TravelItineraryAddInfoRQ.AgencyInfo.Address.StateCountyProv();

        stateCountyProv.setStateCode("TX");
        address.setAddressLine("SABRE TRAVEL");
        address.setCityName("SOUTHLAKE");
        address.setCountryCode("US");
        address.setPostalCode("76092");
        address.setStreetNmbr("3150 SABRE DRIVE");
        address.setStateCountyProv(stateCountyProv);

        ticketing.setTicketType("7T-A");
        agencyInfo.setTicketing(ticketing);

        return agencyInfo;
    }

}
