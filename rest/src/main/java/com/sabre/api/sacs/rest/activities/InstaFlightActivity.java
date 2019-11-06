package com.sabre.api.sacs.rest.activities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sabre.api.sacs.configuration.SacsConfiguration;
import com.sabre.api.sacs.rest.common.GenericRestGetCall;
import com.sabre.api.sacs.rest.domain.instaflight_gen.InstaFlightResponse;
import com.sabre.api.sacs.rest.domain.leadpricecalendar.LeadPriceCalendarRequest;
import com.sabre.api.sacs.rest.domain.leadpricecalendar.LeadPriceCalendarResponse;
import com.sabre.api.sacs.rest.domain.leadpricecalendar.Link;
import com.sabre.api.sacs.workflow.Activity;
import com.sabre.api.sacs.workflow.SharedContext;

/**
 * Activity to use in workflow. It runs the InstaFlight call.
 * It uses the result of the previous call - LeadPriceCalendar - to
 * create the request parameters.
 */
@Controller
public class InstaFlightActivity implements Activity {

    @Autowired
    private SacsConfiguration config;
    
    @Autowired
    private BargainFinderMaxActivity next;
    
    @Autowired
    private GenericRestGetCall<LeadPriceCalendarRequest> call;
    
    @Override
    public Activity run(SharedContext context) {
        
        LeadPriceCalendarResponse lpc = (LeadPriceCalendarResponse) context.getResult("LeadPriceCalendar");
        List<Link> links = lpc.getFareInfo().get(0).getLinks();
        for (Link link: links) {
            if ("shop".equals(link.getRel())) {
                call.setUrl(link.getHref());
                break;
            }
        }
        InstaFlightResponse instaFlight = call.doCall(InstaFlightResponse.class, context);
        context.putResult("InstaFlight", instaFlight);
        return next;
    }

}

    