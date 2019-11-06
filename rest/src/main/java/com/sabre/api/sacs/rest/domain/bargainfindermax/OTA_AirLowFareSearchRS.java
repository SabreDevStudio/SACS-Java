package com.sabre.api.sacs.rest.domain.bargainfindermax;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OTA_AirLowFareSearchRS {

    @JsonProperty("PricedItineraries")
    private PricedItineraries pricedItineraries;

    public PricedItineraries getPricedItineraries() {
        return pricedItineraries;
    }

    public void setPricedItineraries(PricedItineraries pricedItineraries) {
        this.pricedItineraries = pricedItineraries;
    }
}
