package com.sabre.api.sacs.soap.orchestratedflow;

import java.io.StringWriter;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sabre.api.sacs.configuration.SacsConfiguration;
import com.sabre.api.sacs.contract.bargainfindermax.AirSearchPrefsType;
import com.sabre.api.sacs.contract.bargainfindermax.CabinPrefType;
import com.sabre.api.sacs.contract.bargainfindermax.CabinType;
import com.sabre.api.sacs.contract.bargainfindermax.CompanyNameType;
import com.sabre.api.sacs.contract.bargainfindermax.ExchangeOriginDestinationInformationType.SegmentType;
import com.sabre.api.sacs.contract.bargainfindermax.OTAAirLowFareSearchRQ;
import com.sabre.api.sacs.contract.bargainfindermax.OTAAirLowFareSearchRQ.OriginDestinationInformation;
import com.sabre.api.sacs.contract.bargainfindermax.OTAAirLowFareSearchRQ.TPAExtensions;
import com.sabre.api.sacs.contract.bargainfindermax.OTAAirLowFareSearchRS;
import com.sabre.api.sacs.contract.bargainfindermax.OriginDestinationInformationType.DestinationLocation;
import com.sabre.api.sacs.contract.bargainfindermax.OriginDestinationInformationType.OriginLocation;
import com.sabre.api.sacs.contract.bargainfindermax.POSType;
import com.sabre.api.sacs.contract.bargainfindermax.PassengerTypeQuantityType;
import com.sabre.api.sacs.contract.bargainfindermax.PreferLevelType;
import com.sabre.api.sacs.contract.bargainfindermax.SourceType;
import com.sabre.api.sacs.contract.bargainfindermax.TransactionType;
import com.sabre.api.sacs.contract.bargainfindermax.TransactionType.RequestType;
import com.sabre.api.sacs.contract.bargainfindermax.TravelerInfoSummaryType;
import com.sabre.api.sacs.contract.bargainfindermax.TravelerInformationType;
import com.sabre.api.sacs.contract.bargainfindermax.UniqueIDType;
import com.sabre.api.sacs.errors.ErrorHandlingSchedule;
import com.sabre.api.sacs.soap.common.GenericRequestWrapper;
import com.sabre.api.sacs.soap.pool.SessionPool;
import com.sabre.api.sacs.workflow.Activity;
import com.sabre.api.sacs.workflow.SharedContext;

@Controller
@Scope("prototype")
public class BargainFinderMaxSoapActivity implements Activity {

    private static final Logger LOG = LogManager.getLogger(BargainFinderMaxSoapActivity.class);
    
    @Autowired
    private GenericRequestWrapper<OTAAirLowFareSearchRQ, OTAAirLowFareSearchRS> bfm;
    
    @Autowired
    private PassengerDetailsNameOnlyActivity next;
    
    @Autowired
    private ErrorHandlingSchedule errorHandler;
    
    @Autowired
    private SacsConfiguration config;
    
    @Autowired
    private SessionPool sessionPool;
    
    @Override
    public Activity run(SharedContext context) {
        Marshaller marsh;
        try {
            marsh = JAXBContext.newInstance("com.sabre.api.sacs.contract.bargainfindermax").createMarshaller();
            StringWriter sw = new StringWriter();
            OTAAirLowFareSearchRQ request = getRequestBody();
            bfm.setRequest(request);
            bfm.setLastInFlow(false);
            marsh.marshal(request, sw);
            context.putResult("BargainFinderMaxRQ", sw.toString());
            OTAAirLowFareSearchRS result = bfm.executeRequest(context);
            if (result.getErrors() != null && result.getErrors().getError() != null && !result.getErrors().getError().isEmpty()) {
                context.setFaulty(true);
                LOG.warn("Error found, adding context to ErrorHandler. ConversationID: " + context.getConversationId());
                errorHandler.addSystemFailure(context);
                sessionPool.returnToPool(context.getConversationId());
                return null;
            }
            sw = new StringWriter();
            marsh.marshal(result, sw);
            context.putResult("BargainFinderMaxRSObj", result);
            context.putResult("BargainFinderMaxRS", sw.toString());
        } catch (JAXBException e) {
            LOG.error("Error while marshalling the response.", e);
        } catch (InterruptedException e) {
            LOG.catching(e);
        }

        return next;
    }
    
    private OTAAirLowFareSearchRQ getRequestBody() {
        OTAAirLowFareSearchRQ result = new OTAAirLowFareSearchRQ();

        //<POS>
        POSType pos = new POSType();
        SourceType srcType = new SourceType();
        srcType.setPseudoCityCode(config.getSoapProperty("group"));
        
        UniqueIDType uidType = new UniqueIDType();
        uidType.setType("1");
        uidType.setID("1");
        CompanyNameType compNameType = new CompanyNameType();
        compNameType.setCode("TN");
        uidType.setCompanyName(compNameType);
        srcType.setRequestorID(uidType);
        
        pos.getSource().add(srcType);
        result.setPOS(pos);
        
        //<OriginDestinationInformation
        OriginDestinationInformation odi = new OriginDestinationInformation();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        odi.setDepartureDateTime(sdf.format(cal.getTime()));

        OriginLocation ol = new OriginLocation();
        ol.setLocationCode("JFK");
        odi.setOriginLocation(ol);
        
        DestinationLocation dl = new DestinationLocation();
        dl.setLocationCode("LAX");
        odi.setDestinationLocation(dl);
        com.sabre.api.sacs.contract.bargainfindermax.OTAAirLowFareSearchRQ.OriginDestinationInformation.TPAExtensions odiTpa 
            = new com.sabre.api.sacs.contract.bargainfindermax.OTAAirLowFareSearchRQ.OriginDestinationInformation.TPAExtensions();
        SegmentType segType = new SegmentType();
        segType.setCode("O");
        odiTpa.setSegmentType(segType);
        odi.setTPAExtensions(odiTpa);
        result.getOriginDestinationInformation().add(odi);
        
        //<TravelPreferences>
        AirSearchPrefsType travelPreferences = new AirSearchPrefsType();
        travelPreferences.setValidInterlineTicket(true);
        CabinPrefType cabinPref = new CabinPrefType();
        cabinPref.setCabin(CabinType.Y);
        cabinPref.setPreferLevel(PreferLevelType.PREFERRED);
        travelPreferences.getCabinPref().add(cabinPref);
        result.setTravelPreferences(travelPreferences);
        
        //<TravelerInfoSummary>
        TravelerInfoSummaryType tiSummaryType = new TravelerInfoSummaryType();
        tiSummaryType.getSeatsRequested().add(new BigInteger("1"));
        
        TravelerInformationType airTravelerAvail = new TravelerInformationType();
        PassengerTypeQuantityType passengerTypeQuantity = new PassengerTypeQuantityType();
        passengerTypeQuantity.setCode("ADT");
        passengerTypeQuantity.setQuantity(1);
        airTravelerAvail.getPassengerTypeQuantity().add(passengerTypeQuantity );
        
        tiSummaryType.getAirTravelerAvail().add(airTravelerAvail);
        result.setTravelerInfoSummary(tiSummaryType);
        
        //<TPA_Extension>
        TPAExtensions tpa = new TPAExtensions();
        TransactionType intelliSell = new TransactionType();
        RequestType reqType = new RequestType();
        reqType.setName("50ITINS");
        intelliSell.setRequestType(reqType);
        tpa.setIntelliSellTransaction(intelliSell);
        
        result.setTPAExtensions(tpa);
        result.setVersion(config.getSoapProperty("BargainFinderMaxRQVersion"));
        
        return result;
    }

}
