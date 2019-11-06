package com.sabre.api.sacs.soap.wrapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

import com.sabre.api.sacs.configuration.SacsConfiguration;
import com.sabre.api.sacs.contract.travelitinerary.TravelItineraryReadRQ;
import com.sabre.api.sacs.contract.travelitinerary.TravelItineraryReadRS;
import com.sabre.api.sacs.soap.callback.HeaderCallback;
import com.sabre.api.sacs.soap.callback.HeaderComposingCallback;
import com.sabre.api.sacs.soap.common.GenericRequestWrapper;

/**
 * Wrapper class implementing the {@link GenericRequestWrapper}, configuring it to call
 * the TravelItineraryRead service.
 */
@Controller
@Scope("prototype")
public class TravelItineraryReadWrapper extends GenericRequestWrapper<TravelItineraryReadRQ, TravelItineraryReadRS> {

    @Autowired
    private SacsConfiguration configuration;

    @Autowired
    @Qualifier("travelItineraryHeaderComposingCallback")
    private HeaderComposingCallback travelItineraryCallback;


    @Override
    protected List<ClientInterceptor> interceptors() {
        return null;    
    }

    @Override
    protected Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.sabre.api.sacs.contract.travelitinerary");
        return marshaller;
    }

    @Override
    protected HeaderCallback callback() {
        return travelItineraryCallback;
    }

}
