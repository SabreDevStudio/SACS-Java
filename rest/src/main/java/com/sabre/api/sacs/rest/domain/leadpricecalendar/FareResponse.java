package com.sabre.api.sacs.rest.domain.leadpricecalendar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FareResponse {

    @JsonProperty("AirlineCodes")
    private List<String> airlineCodes;

    @JsonProperty("Fare")
    private BigDecimal fare;

    public FareResponse() {
    }
    
    public FareResponse(String input) {
        if ("N/A".equals(input)) {
            this.airlineCodes = new ArrayList<>();
            this.airlineCodes.add(input);
        }
    }
    
    public List<String> getAirlineCodes() {
        return airlineCodes;
    }

    public void setAirlineCodes(List<String> airlineCodes) {
        this.airlineCodes = airlineCodes;
    }

    public BigDecimal getFare() {
        return fare;
    }

    public void setFare(BigDecimal fare) {
        this.fare = fare;
    }
}
