package com.sabre.api.sacs.rest.activities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sabre.api.sacs.configuration.SacsConfiguration;
import com.sabre.api.sacs.rest.common.GenericRestGetCall;
import com.sabre.api.sacs.rest.domain.leadpricecalendar.LeadPriceCalendarRequest;
import com.sabre.api.sacs.rest.domain.leadpricecalendar.LeadPriceCalendarResponse;
import com.sabre.api.sacs.workflow.Activity;
import com.sabre.api.sacs.workflow.SharedContext;

/**
 * Activity to use in workflow. It runs the LeadPriceCalendarActivity.
 */
@Controller
public class LeadPriceCalendarActivity implements Activity {

    @Autowired
    private SacsConfiguration config;
    
    @Autowired
    private InstaFlightActivity next;
    
    @Autowired
    private GenericRestGetCall<LeadPriceCalendarRequest> call;

    @Override
    public Activity run(SharedContext context) {
        LeadPriceCalendarRequest request = new LeadPriceCalendarRequest.Builder()
                .origin("LAX")
                .destination("JFK")
                .lengthOfStay(5)
                .pointOfSaleCountry("US")
                .build();
        call.setUrl(config.getRestProperty("environment") + "/v2/shop/flights/fares");
        call.setRequest(request);
        LeadPriceCalendarResponse leadPriceCalendar = call.doCall(LeadPriceCalendarResponse.class, context);
        context.putResult("LeadPriceCalendar", leadPriceCalendar);
        return next;
    }

}
