package com.sabre.api.sacs.rest.domain.bargainfindermax;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BargainFinderMaxResponse {

    @JsonProperty("OTA_AirLowFareSearchRS")
    private OTA_AirLowFareSearchRS otaAirLowFareSearchRS;

    public OTA_AirLowFareSearchRS getOtaAirLowFareSearchRS() {
        return otaAirLowFareSearchRS;
    }

    public void setOtaAirLowFareSearchRS(OTA_AirLowFareSearchRS otaAirLowFareSearchRS) {
        this.otaAirLowFareSearchRS = otaAirLowFareSearchRS;
    }
    
    
}
