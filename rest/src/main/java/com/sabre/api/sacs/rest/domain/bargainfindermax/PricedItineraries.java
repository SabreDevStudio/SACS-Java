package com.sabre.api.sacs.rest.domain.bargainfindermax;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sabre.api.sacs.rest.domain.instaflight_gen.PricedItinerary;

public class PricedItineraries {

    @JsonProperty("PricedItinerary")
    private List<PricedItinerary> pricedItinerary;
}
