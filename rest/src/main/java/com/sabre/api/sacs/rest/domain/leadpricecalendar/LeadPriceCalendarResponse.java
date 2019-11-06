package com.sabre.api.sacs.rest.domain.leadpricecalendar;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LeadPriceCalendarResponse {

//    {
//        "OriginLocation": "JFK",
//        "DestinationLocation": "LAX",
//        "FareInfo": [{
//            "LowestFare": {
//                "AirlineCodes": ["SY"],
//                "Fare": 427.2
//            },
//            "CurrencyCode": "USD",
//            "LowestNonStopFare": {
//                "AirlineCodes": ["B6"],
//                "Fare": 1138.2
//            },
//            "DepartureDateTime": "2015-08-04T00:00:00",
//            "ReturnDateTime": "2015-08-09T00:00:00",
//            "Links": [{
//                "rel": "shop",
//                "href": "https://api.test.sabre.com/v1/shop/flights?origin=JFK&destination=LAX&departuredate=2015-08-04&returndate=2015-08-09&pointofsalecountry=US"
//            }]
//        }
    
    @JsonProperty("OriginLocation")
    private String originLocation;
    
    @JsonProperty("DestinationLocation")
    private String destinationLocation;
    
    @JsonProperty("FareInfo")
    private List<FareInfoResponse> fareInfo;
    
    @JsonProperty("Links")
    private List<Link> links;

    public String getOriginLocation() {
        return originLocation;
    }

    public void setOriginLocation(String originLocation) {
        this.originLocation = originLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public List<FareInfoResponse> getFareInfo() {
        return fareInfo;
    }

    public void setFareInfo(List<FareInfoResponse> fareInfo) {
        this.fareInfo = fareInfo;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
