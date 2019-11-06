package com.sabre.api.sacs.soap;

import static org.springframework.ws.test.client.RequestMatchers.payload;
import static org.springframework.ws.test.client.ResponseCreators.withSoapEnvelope;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

import com.sabre.api.sacs.configuration.SacsConfiguration;
import com.sabre.api.sacs.contract.travelitinerary.TravelItineraryReadRQ;
import com.sabre.api.sacs.contract.travelitinerary.TravelItineraryReadRS;
import com.sabre.api.sacs.soap.common.GenericRequestWrapper;
import com.sabre.api.sacs.workflow.SharedContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SoapApplicationConfiguration.class })
public class TravelItineraryReadTest {

    @Autowired
    private GenericRequestWrapper<TravelItineraryReadRQ, TravelItineraryReadRS> tir;
    
    @Autowired
    private SacsConfiguration configuration;

    private SharedContext context = new SharedContext();
    
    private MockWebServiceServer mockServer;
    
    @Before
    public void setUp() {
        mockServer = MockWebServiceServer.createServer(tir);
        context.setConversationId("EFGH");
    }
    
    @Test
    public void testCall() throws IOException, InterruptedException {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.sabre.api.sacs.contract.travelitinerary");
        Result marshalledRequest = new StringResult();
        marshaller.marshal(getRequestBody("ABCD"), marshalledRequest);
        Source requestPayload = new StringSource(marshalledRequest.toString());
        Source responsePayload = new StringSource(getResponseBody());
        mockServer.expect(payload(requestPayload)).andRespond(withSoapEnvelope(responsePayload));
        tir.setRequest(getRequestBody("ABCD"));
        tir.setLastInFlow(true);
        TravelItineraryReadRS response = tir.executeRequest(context);
        Assert.assertEquals(configuration.getSoapProperty("TravelItineraryReadRQVersion"), response.getVersion());
        mockServer.verify();
    }
    
    private String getResponseBody() throws IOException {
        StringBuilder response = new StringBuilder();
        BufferedReader rdr = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("TravelItineraryReadResponse.xml")));
        String line;
        while ((line = rdr.readLine()) != null) {
            response.append(line);
        }
        return response.toString();
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

    