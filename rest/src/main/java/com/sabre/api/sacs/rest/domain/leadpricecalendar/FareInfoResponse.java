package com.sabre.api.sacs.rest.domain.leadpricecalendar;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FareInfoResponse {
/*
{
        "LowestFare": {
            "AirlineCodes": ["SY"],
            "Fare": 427.2
        },
        "CurrencyCode": "USD",
        "LowestNonStopFare": {
            "AirlineCodes": ["B6"],
            "Fare": 1138.2
        },
        "DepartureDateTime": "2015-08-04T00:00:00",
        "ReturnDateTime": "2015-08-09T00:00:00",
        "Links": [{
            "rel": "shop",
            "href": "https://api.test.sabre.com/v1/shop/flights?origin=JFK&destination=LAX&departuredate=2015-08-04&returndate=2015-08-09&pointofsalecountry=US"
        } */
    
    @JsonProperty("LowestFare")
    private FareResponse lowestFare;
    
    @JsonProperty("LowestNonStopFare")
    private FareResponse lowestNonStopFare;
    
    @JsonProperty("CurrencyCode")
    private String currencyCode;
    
    @JsonProperty("DepartureDateTime")
    private Date departureDateTime;

    @JsonProperty("ReturnDateTime")
    private Date returnDateTime;
    
    @JsonProperty("Links")
    private List<Link> links;

    public FareResponse getLowestFare() {
        return lowestFare;
    }

    public void setLowestFare(FareResponse lowestFare) {
        this.lowestFare = lowestFare;
    }

    public FareResponse getLowestNonStopFare() {
        return lowestNonStopFare;
    }

    public void setLowestNonStopFare(FareResponse lowestNonStopFare) {
        this.lowestNonStopFare = lowestNonStopFare;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Date getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(Date departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Date getReturnDateTime() {
        return returnDateTime;
    }

    public void setReturnDateTime(Date returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

}
