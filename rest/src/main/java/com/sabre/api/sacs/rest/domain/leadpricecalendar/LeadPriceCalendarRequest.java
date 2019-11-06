package com.sabre.api.sacs.rest.domain.leadpricecalendar;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sabre.api.sacs.rest.domain.BaseDomainRequest;

public class LeadPriceCalendarRequest extends BaseDomainRequest {

    private String origin;
    private String destination;
    
    @JsonProperty("lengthofstay")
    private Integer lengthOfStay;
    
    @JsonProperty("departuredate")
    private String departureDate;
    
    @JsonProperty("minfare")
    private String minFare;
    
    @JsonProperty("maxfare")
    private String maxFare;
    
    @JsonProperty("pointofsalecountry")
    private String pointOfSaleCountry;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getLengthOfStay() {
        return lengthOfStay;
    }

    public void setLengthOfStay(Integer lengthOfStay) {
        this.lengthOfStay = lengthOfStay;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getMinFare() {
        return minFare;
    }

    public void setMinFare(String minFare) {
        this.minFare = minFare;
    }

    public String getMaxFare() {
        return maxFare;
    }

    public void setMaxFare(String maxFare) {
        this.maxFare = maxFare;
    }

    public String getPointOfSaleCountry() {
        return pointOfSaleCountry;
    }

    public void setPointOfSaleCountry(String pointOfSaleCountry) {
        this.pointOfSaleCountry = pointOfSaleCountry;
    }
    
    public Map<String, ?> toParameterMap() {
        Map<String, Object> result = new HashMap<>();
        
        result.put("origin", origin);
        result.put("destination", destination);
        result.put("lengthofstay", lengthOfStay);
        result.put("departuredate", departureDate);
        result.put("minfare", minFare);
        result.put("maxfare", maxFare);
        result.put("pointofsalecountry", pointOfSaleCountry);
        return result;
    }
    
    public static class Builder {
        
        private LeadPriceCalendarRequest _obj = new LeadPriceCalendarRequest();
        
        public LeadPriceCalendarRequest build() {
            return _obj;
        }
        
        public Builder origin(String origin) {
            _obj.origin = origin;
            return this;
        }
        public Builder destination(String destination) {
            _obj.destination = destination;
            return this;
        }
        
        public Builder lengthOfStay(Integer lengthOfStay) {
            _obj.lengthOfStay = lengthOfStay;
            return this;
        }
        
        public Builder departureDate(String departureDate) {
            _obj.departureDate = departureDate;
            return this;
        }
        
        public Builder minFare(String minFare) {
            _obj.minFare = minFare;
            return this;
        }
        
        public Builder maxFare(String maxFare) {
            _obj.maxFare = maxFare;
            return this;
        }
        
        public Builder pointOfSaleCountry(String pointOfSaleCountry) {
            _obj.pointOfSaleCountry = pointOfSaleCountry;
            return this;
        }
   }
}
