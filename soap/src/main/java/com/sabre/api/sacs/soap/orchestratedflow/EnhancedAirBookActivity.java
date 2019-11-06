package com.sabre.api.sacs.soap.orchestratedflow;

import java.io.StringWriter;
import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sabre.api.sacs.configuration.SacsConfiguration;
import com.sabre.api.sacs.contract.bargainfindermax.BookFlightSegmentType;
import com.sabre.api.sacs.contract.bargainfindermax.OTAAirLowFareSearchRS;
import com.sabre.api.sacs.contract.bargainfindermax.OriginDestinationOptionType;
import com.sabre.api.sacs.contract.enhancedairbook.EnhancedAirBookRQ;
import com.sabre.api.sacs.contract.enhancedairbook.EnhancedAirBookRS;
import com.sabre.api.sacs.errors.ErrorHandlingSchedule;
import com.sabre.api.sacs.soap.common.GenericRequestWrapper;
import com.sabre.api.sacs.soap.pool.SessionPool;
import com.sabre.api.sacs.workflow.Activity;
import com.sabre.api.sacs.workflow.SharedContext;

@Controller
@Scope("prototype")
public class EnhancedAirBookActivity implements Activity {

    private static final Logger LOG = LogManager.getLogger(EnhancedAirBookActivity.class);

    @Autowired
    private SacsConfiguration config;
    
    @Autowired
    private PassengerDetailsActivity next;

    @Autowired
    private ErrorHandlingSchedule errorHandler;
    
    @Autowired
    private GenericRequestWrapper<EnhancedAirBookRQ, EnhancedAirBookRS> eab;
    
    @Autowired
    private SessionPool sessionPool;
    
    @Override
    public Activity run(SharedContext context) {
        Marshaller marsh;
        try {
            marsh = JAXBContext.newInstance("com.sabre.api.sacs.contract.enhancedairbook").createMarshaller();
            StringWriter sw = new StringWriter();
            
            OTAAirLowFareSearchRS bfmResult = (OTAAirLowFareSearchRS) context.getResult("BargainFinderMaxRSObj");
            EnhancedAirBookRQ request = getRequestBody(bfmResult);
            eab.setRequest(request);
            eab.setLastInFlow(false);
            EnhancedAirBookRS result = eab.executeRequest(context);
            marsh.marshal(request, sw);
            context.putResult("EnhancedAirBookRQ", sw.toString());
            if (result.getApplicationResults() != null && result.getApplicationResults().getError() != null && !result.getApplicationResults().getError().isEmpty()) {
                context.setFaulty(true);
                LOG.warn("Error found, adding context to ErrorHandler. ConversationID: " + context.getConversationId());
                errorHandler.addSystemFailure(context);
                sessionPool.returnToPool(context.getConversationId());
                return null;
            }
            sw = new StringWriter();
            marsh.marshal(result, sw);
            context.putResult("EnhancedAirBookRS", sw.toString());
        } catch (JAXBException e) {
            LOG.error("Error while marshalling the response.", e);
        } catch (InterruptedException e) {
            LOG.catching(e);
        }

        return next;
    }

    private EnhancedAirBookRQ getRequestBody(OTAAirLowFareSearchRS bfmResult) {

        EnhancedAirBookRQ requestBody = new EnhancedAirBookRQ();
        requestBody.setHaltOnError(false);
        requestBody.setVersion(config.getSoapProperty("EnhancedAirBookRQVersion"));

        requestBody.setOTAAirBookRQ(getOTAAirBookRQ(bfmResult));
        requestBody.setOTAAirPriceRQ(getOTAAirPriceRQ());
        requestBody.setPostProcessing(getPostProcessing());

        return requestBody;
    }

    private EnhancedAirBookRQ.OTAAirBookRQ getOTAAirBookRQ(OTAAirLowFareSearchRS bfmResult) {

        EnhancedAirBookRQ.OTAAirBookRQ airBookRQ = new EnhancedAirBookRQ.OTAAirBookRQ();

        EnhancedAirBookRQ.OTAAirBookRQ.OriginDestinationInformation originDestinationInformation = new EnhancedAirBookRQ.OTAAirBookRQ.OriginDestinationInformation();
        //options from first priced itinerary
        List<OriginDestinationOptionType> originDestinationOption = bfmResult.getPricedItineraries().getPricedItinerary().get(0).getAirItinerary().getOriginDestinationOptions().getOriginDestinationOption();
        originDestinationInformation.getFlightSegment().add(getFlightSegment(originDestinationOption));
        airBookRQ.setOriginDestinationInformation(originDestinationInformation);

        return airBookRQ;
    }

    private EnhancedAirBookRQ.OTAAirBookRQ.OriginDestinationInformation.FlightSegment getFlightSegment(List<OriginDestinationOptionType> originDestinationOption) {

        EnhancedAirBookRQ.OTAAirBookRQ.OriginDestinationInformation.FlightSegment flightSegment = new EnhancedAirBookRQ.OTAAirBookRQ.OriginDestinationInformation.FlightSegment();
        
        BookFlightSegmentType bfst = originDestinationOption.get(0).getFlightSegment().get(0);
        EnhancedAirBookRQ.OTAAirBookRQ.OriginDestinationInformation.FlightSegment.DestinationLocation destinationLocation = new EnhancedAirBookRQ.OTAAirBookRQ.OriginDestinationInformation.FlightSegment.DestinationLocation();
        EnhancedAirBookRQ.OTAAirBookRQ.OriginDestinationInformation.FlightSegment.Equipment equipment = new EnhancedAirBookRQ.OTAAirBookRQ.OriginDestinationInformation.FlightSegment.Equipment();
        EnhancedAirBookRQ.OTAAirBookRQ.OriginDestinationInformation.FlightSegment.MarketingAirline marketingAirline = new EnhancedAirBookRQ.OTAAirBookRQ.OriginDestinationInformation.FlightSegment.MarketingAirline();
        EnhancedAirBookRQ.OTAAirBookRQ.OriginDestinationInformation.FlightSegment.OperatingAirline operatingAirline = new EnhancedAirBookRQ.OTAAirBookRQ.OriginDestinationInformation.FlightSegment.OperatingAirline();
        EnhancedAirBookRQ.OTAAirBookRQ.OriginDestinationInformation.FlightSegment.OriginLocation originLocation = new EnhancedAirBookRQ.OTAAirBookRQ.OriginDestinationInformation.FlightSegment.OriginLocation();

        destinationLocation.setLocationCode(bfst.getArrivalAirport().getLocationCode());
        equipment.setAirEquipType(bfst.getEquipment().get(0).getAirEquipType());
        marketingAirline.setCode(bfst.getMarketingAirline().getCode());
        marketingAirline.setFlightNumber(bfst.getFlightNumber());
        operatingAirline.setCode(bfst.getOperatingAirline().getCode());
        originLocation.setLocationCode(bfst.getDepartureAirport().getLocationCode());

        flightSegment.setDestinationLocation(destinationLocation);
        flightSegment.setEquipment(equipment);
        flightSegment.setOperatingAirline(operatingAirline);
        flightSegment.setMarketingAirline(marketingAirline);
        flightSegment.setOriginLocation(originLocation);

        flightSegment.setFlightNumber(bfst.getFlightNumber());
        flightSegment.setDepartureDateTime(bfst.getDepartureDateTime());
        flightSegment.setNumberInParty(bfst.getNumberInParty()==null?"1":bfst.getNumberInParty().toString());
        flightSegment.setStatus("NN");
        flightSegment.setResBookDesigCode(bfst.getResBookDesigCode());


        return flightSegment;
    }

    private EnhancedAirBookRQ.OTAAirPriceRQ getOTAAirPriceRQ() {

        EnhancedAirBookRQ.OTAAirPriceRQ airPriceRQ = new EnhancedAirBookRQ.OTAAirPriceRQ();

        EnhancedAirBookRQ.OTAAirPriceRQ.PriceRequestInformation priceRequestInformation = new EnhancedAirBookRQ.OTAAirPriceRQ.PriceRequestInformation();
        priceRequestInformation.setRetain(true);

        EnhancedAirBookRQ.OTAAirPriceRQ.PriceRequestInformation.OptionalQualifiers optionalQualifiers = new EnhancedAirBookRQ.OTAAirPriceRQ.PriceRequestInformation.OptionalQualifiers();
        EnhancedAirBookRQ.OTAAirPriceRQ.PriceRequestInformation.OptionalQualifiers.PricingQualifiers pricingQualifiers = new EnhancedAirBookRQ.OTAAirPriceRQ.PriceRequestInformation.OptionalQualifiers.PricingQualifiers();

        EnhancedAirBookRQ.OTAAirPriceRQ.PriceRequestInformation.OptionalQualifiers.PricingQualifiers.PassengerType passengerType = new EnhancedAirBookRQ.OTAAirPriceRQ.PriceRequestInformation.OptionalQualifiers.PricingQualifiers.PassengerType();

        passengerType.setCode("ADT");
        passengerType.setQuantity("1");
        pricingQualifiers.getPassengerType().add(passengerType);

        optionalQualifiers.setPricingQualifiers(pricingQualifiers);
        priceRequestInformation.setOptionalQualifiers(optionalQualifiers);
        airPriceRQ.setPriceRequestInformation(priceRequestInformation);

        return airPriceRQ;
    }

    private EnhancedAirBookRQ.PostProcessing getPostProcessing() {

        EnhancedAirBookRQ.PostProcessing postProcessing = new EnhancedAirBookRQ.PostProcessing();
        EnhancedAirBookRQ.PostProcessing.RedisplayReservation redisplayReservation = new EnhancedAirBookRQ.PostProcessing.RedisplayReservation();

        redisplayReservation.setWaitInterval(new BigInteger("2000"));
        postProcessing.setRedisplayReservation(redisplayReservation);

        return postProcessing;
    }

}
