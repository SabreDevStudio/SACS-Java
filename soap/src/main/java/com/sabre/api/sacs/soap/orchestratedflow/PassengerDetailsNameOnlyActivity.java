package com.sabre.api.sacs.soap.orchestratedflow;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.RandomStringUtils;
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
public class PassengerDetailsNameOnlyActivity implements Activity {

    private static final Logger LOG = LogManager.getLogger(PassengerDetailsNameOnlyActivity.class);
    
	@Autowired
	private GenericRequestWrapper<PassengerDetailsRQ, PassengerDetailsRS> pd;
	
	@Autowired
	private EnhancedAirBookActivity next;
	
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
			context.putResult("PassengerDetailsNameOnlyRQ", sw.toString());
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
			context.putResult("PassengerDetailsNameOnlyRSW", sw.toString());
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

        request.setTravelItineraryAddInfoRQ(getTravelItineraryAddInfoRQ());

        return request;

    }



    private PassengerDetailsRQ.TravelItineraryAddInfoRQ getTravelItineraryAddInfoRQ() {

        PassengerDetailsRQ.TravelItineraryAddInfoRQ travelItineraryAddInfoRQ = new PassengerDetailsRQ.TravelItineraryAddInfoRQ();
        PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo customerInfo = new PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo();

        customerInfo.setContactNumbers(getContactNumbers());

        PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo.Email email = new PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo.Email();
        email.setAddress("webservices.support@sabre.com");
        email.setNameNumber("1.1");

        customerInfo.getEmail().add(email);
        PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo.PersonName personName1 = new PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo.PersonName();
        personName1.setNameNumber("1.1");
        personName1.setGivenName("SACS"+RandomStringUtils.randomAlphabetic(4));
        personName1.setSurname("TEST"+RandomStringUtils.randomAlphabetic(4));
        customerInfo.getPersonName().add(personName1);
        travelItineraryAddInfoRQ.setCustomerInfo(customerInfo);

        return travelItineraryAddInfoRQ;
    }


    private PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo.ContactNumbers getContactNumbers() {

        PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo.ContactNumbers contactNumbers = new PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo.ContactNumbers();

        PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo.ContactNumbers.ContactNumber contactNumber1 = new PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo.ContactNumbers.ContactNumber();
        PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo.ContactNumbers.ContactNumber contactNumber2 = new PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo.ContactNumbers.ContactNumber();

        contactNumber1.setLocationCode("DFW");
        contactNumber1.setNameNumber("1.1");
        contactNumber1.setPhone("817-555-1212");
        contactNumber1.setPhoneUseType("H");

        contactNumber2.setLocationCode("DFW");
        contactNumber2.setNameNumber("1.1");
        contactNumber2.setPhone("682-555-1212");
        contactNumber2.setPhoneUseType("O");

        contactNumbers.getContactNumber().add(contactNumber1);
        contactNumbers.getContactNumber().add(contactNumber2);

        return contactNumbers;
    }


}
