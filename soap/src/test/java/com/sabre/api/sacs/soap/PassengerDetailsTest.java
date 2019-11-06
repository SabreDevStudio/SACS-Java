package com.sabre.api.sacs.soap;

import static org.springframework.ws.test.client.RequestMatchers.anything;
import static org.springframework.ws.test.client.ResponseCreators.withSoapEnvelope;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

import javax.xml.transform.Source;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.xml.transform.StringSource;

import com.sabre.api.sacs.configuration.SacsConfiguration;
import com.sabre.api.sacs.contract.passengerdetails.PassengerDetailsRQ;
import com.sabre.api.sacs.contract.passengerdetails.PassengerDetailsRS;
import com.sabre.api.sacs.soap.SoapApplicationConfiguration;
import com.sabre.api.sacs.soap.common.GenericRequestWrapper;
import com.sabre.api.sacs.soap.pool.SessionPool;
import com.sabre.api.sacs.workflow.SharedContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SoapApplicationConfiguration.class })
public class PassengerDetailsTest {

    @Autowired
    private GenericRequestWrapper<PassengerDetailsRQ, PassengerDetailsRS> pd;

    @Autowired
    private SacsConfiguration configuration;
    
    @Autowired
    private SessionPool sessionPool;

    private SharedContext context = new SharedContext();

    private MockWebServiceServer mockServer;

    @Before
    public void setUp() {
        mockServer = MockWebServiceServer.createServer(pd);
        context.setConversationId("EFGH");
    }

    @Test
    public void testCall() throws IOException, InterruptedException {
        Source responsePayload = new StringSource(getResponseBody());
        mockServer.expect(anything()).andRespond(withSoapEnvelope(responsePayload));
        pd.setRequest(getRequestBody());
        pd.setLastInFlow(true);
        PassengerDetailsRS response = pd.executeRequest(context);
        Assert.assertEquals("LTUSTX", response.getItineraryRef().getID());
        mockServer.verify();
    }

    private String getResponseBody() throws IOException {
        StringBuilder response = new StringBuilder();
        BufferedReader rdr = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader()
                .getResourceAsStream("PassengerDetailsResponse.xml")));
        String line;
        while ((line = rdr.readLine()) != null) {
            response.append(line);
        }
        return response.toString();
    }

    private String getRequestBodyString() throws IOException {
        StringBuilder request = new StringBuilder();
        BufferedReader rdr = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader()
                .getResourceAsStream("PassengerDetailsRequest.xml")));
        String line;
        while ((line = rdr.readLine()) != null) {
            request.append(line);
        }
        return request.toString();
    }

    private PassengerDetailsRQ getRequestBody() {

        PassengerDetailsRQ request = new PassengerDetailsRQ();
        request.setIgnoreOnError(false);
        request.setHaltOnError(true);
        request.setVersion(configuration.getSoapProperty("PassengerDetailsRQVersion"));

        request.setMiscSegmentSellRQ(getMiscSegmentSellRQ());
        request.setPostProcessing(getPostProcessing());
        request.setSpecialReqDetails(getSpecialReqDetails());
        request.setTravelItineraryAddInfoRQ(getTravelItineraryAddInfoRQ());

        return request;

    }

    private PassengerDetailsRQ.MiscSegmentSellRQ getMiscSegmentSellRQ() {

        PassengerDetailsRQ.MiscSegmentSellRQ miscSegmentSellRQ = new PassengerDetailsRQ.MiscSegmentSellRQ();
        PassengerDetailsRQ.MiscSegmentSellRQ.MiscSegment miscSegment = new PassengerDetailsRQ.MiscSegmentSellRQ.MiscSegment();
        miscSegment.setText("RETENTION SEGMENT");
        miscSegment.setType("OTH");
        miscSegment.setDepartureDateTime("2015-02-27");
        miscSegment.setNumberInParty(new BigInteger("1"));
        miscSegment.setStatus("HK");
        PassengerDetailsRQ.MiscSegmentSellRQ.MiscSegment.OriginLocation originLocation = new PassengerDetailsRQ.MiscSegmentSellRQ.MiscSegment.OriginLocation();
        PassengerDetailsRQ.MiscSegmentSellRQ.MiscSegment.VendorPrefs vendorPrefs = new PassengerDetailsRQ.MiscSegmentSellRQ.MiscSegment.VendorPrefs();
        PassengerDetailsRQ.MiscSegmentSellRQ.MiscSegment.VendorPrefs.Airline airline = new PassengerDetailsRQ.MiscSegmentSellRQ.MiscSegment.VendorPrefs.Airline();

        originLocation.setLocationCode("DFW");
        airline.setCode("DL");
        vendorPrefs.setAirline(airline);

        miscSegment.setOriginLocation(originLocation);
        miscSegment.setVendorPrefs(vendorPrefs);
        miscSegmentSellRQ.setMiscSegment(miscSegment);

        return miscSegmentSellRQ;
    }

    private PassengerDetailsRQ.PostProcessing getPostProcessing() {

        PassengerDetailsRQ.PostProcessing postProcessing = new PassengerDetailsRQ.PostProcessing();
        postProcessing.setRedisplayReservation(true);
        PassengerDetailsRQ.PostProcessing.EndTransactionRQ endTransactionRQ = new PassengerDetailsRQ.PostProcessing.EndTransactionRQ();
        PassengerDetailsRQ.PostProcessing.EndTransactionRQ.Source source = new PassengerDetailsRQ.PostProcessing.EndTransactionRQ.Source();
        PassengerDetailsRQ.PostProcessing.EndTransactionRQ.EndTransaction endTransaction = new PassengerDetailsRQ.PostProcessing.EndTransactionRQ.EndTransaction();
        source.setReceivedFrom("SWSTesting");
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
        personName.setGivenName("SWS");
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
        PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo customerInfo = new PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo();

        customerInfo.setContactNumbers(getContactNumbers());

        PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo.Email email = new PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo.Email();
        email.setAddress("webservices.support@sabre.com");
        email.setNameNumber("1.1");

        customerInfo.getEmail().add(email);
        PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo.PersonName personName1 = new PassengerDetailsRQ.TravelItineraryAddInfoRQ.CustomerInfo.PersonName();
        personName1.setNameNumber("1.1");
        personName1.setGivenName("SWS" + RandomStringUtils.randomAlphabetic(4));
        personName1.setSurname("TEST" + RandomStringUtils.randomAlphabetic(4));
        customerInfo.getPersonName().add(personName1);
        travelItineraryAddInfoRQ.setCustomerInfo(customerInfo);
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
        // agencyInfo.setAddress(address);
        agencyInfo.setTicketing(ticketing);

        return agencyInfo;
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
