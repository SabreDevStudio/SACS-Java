package com.sabre.api.sacs.rest.activities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sabre.api.sacs.rest.domain.generated.BfmV186Response;
import com.sabre.api.sacs.configuration.SacsConfiguration;
import com.sabre.api.sacs.rest.common.GenericRestPostCall;
import com.sabre.api.sacs.rest.domain.bargainfindermax.AirTravelerAvail;
import com.sabre.api.sacs.rest.domain.bargainfindermax.BargainFinderMaxRequest;
import com.sabre.api.sacs.rest.domain.bargainfindermax.CompanyName;
import com.sabre.api.sacs.rest.domain.bargainfindermax.DestinationLocation;
import com.sabre.api.sacs.rest.domain.bargainfindermax.IntelliSellTransaction;
import com.sabre.api.sacs.rest.domain.bargainfindermax.OTAAirLowFareSearchRQ;
import com.sabre.api.sacs.rest.domain.bargainfindermax.OriginDestinationInformation;
import com.sabre.api.sacs.rest.domain.bargainfindermax.OriginLocation;
import com.sabre.api.sacs.rest.domain.bargainfindermax.POS;
import com.sabre.api.sacs.rest.domain.bargainfindermax.PassengerTypeQuantity;
import com.sabre.api.sacs.rest.domain.bargainfindermax.RequestType;
import com.sabre.api.sacs.rest.domain.bargainfindermax.RequestorID;
import com.sabre.api.sacs.rest.domain.bargainfindermax.Source;
import com.sabre.api.sacs.rest.domain.bargainfindermax.TPAExtensions;
import com.sabre.api.sacs.rest.domain.bargainfindermax.TravelerInfoSummary;
import com.sabre.api.sacs.workflow.Activity;
import com.sabre.api.sacs.workflow.SharedContext;

/**
 * Activity to use in workflow. It runs the BargainFinderMax call.
 * Last one in example flow.
 */
@Controller
public class BargainFinderMaxActivity implements Activity {

    @Autowired
    private SacsConfiguration config;
    
    @Autowired
    private GenericRestPostCall<BargainFinderMaxRequest> call;

    @Override
    public Activity run(SharedContext context) {
        call.setRequest(generateRequest());
        call.setUrl(config.getRestProperty("environment") + "/v1.8.6/shop/flights?mode=live");
        BfmV186Response response = call.doCall(BfmV186Response.class, context);
        context.putResult("BargainFinderMaxResponse", response);
        return null;
    }

    private BargainFinderMaxRequest generateRequest() {
        List<OriginDestinationInformation> originDestinationInfos = new ArrayList<>();
        OriginDestinationInformation odInfo = new OriginDestinationInformation()
                .withDepartureDateTime("2015-10-05T00:00:00")
                .withOriginLocation(new OriginLocation()
                        .withLocationCode("LAX"))
                .withDestinationLocation(new DestinationLocation()
                        .withLocationCode("JFK"))
                .withRPH("1");
        List<Source> sourceList = new ArrayList<>();
        Source source = new Source()
                .withRequestorID(new RequestorID()
                        .withID("REQ.ID")
                        .withType("0.AAA.X")
                        .withCompanyName(new CompanyName()
                                .withCode("TN")));
        sourceList.add(source);
        POS pos = new POS()
                .withSource(sourceList);
        originDestinationInfos.add(odInfo);

        List<PassengerTypeQuantity> passengerTypeQuantities = new ArrayList<>();
        PassengerTypeQuantity ptQuantity = new PassengerTypeQuantity()
                .withCode("ADT")
                .withQuantity(1);
        passengerTypeQuantities.add(ptQuantity);
        List<AirTravelerAvail> airTravelerAvails = new ArrayList<>();
        AirTravelerAvail atAvail = new AirTravelerAvail()
                .withPassengerTypeQuantity(passengerTypeQuantities);
        airTravelerAvails.add(atAvail);
        BargainFinderMaxRequest request = new BargainFinderMaxRequest()
                .withOTAAirLowFareSearchRQ(new OTAAirLowFareSearchRQ()
                        .withOriginDestinationInformation(originDestinationInfos)
                        .withPOS(pos)
                        .withTPAExtensions(new TPAExtensions()
                                .withIntelliSellTransaction(new IntelliSellTransaction()
                                        .withRequestType(new RequestType()
                                                .withName("50ITINS"))))
                        .withTravelerInfoSummary(new TravelerInfoSummary()
                                .withAirTravelerAvail(airTravelerAvails)));
        return request;
    }

}
