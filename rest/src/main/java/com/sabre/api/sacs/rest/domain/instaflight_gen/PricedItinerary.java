
package com.sabre.api.sacs.rest.domain.instaflight_gen;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "AirItinerary",
    "TPA_Extensions",
    "SequenceNumber",
    "AirItineraryPricingInfo",
    "TicketingInfo"
})
public class PricedItinerary {

    @JsonProperty("AirItinerary")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.AirItinerary AirItinerary;
    @JsonProperty("TPA_Extensions")
    private TPA_Extensions_ TPA_Extensions;
    @JsonProperty("SequenceNumber")
    private Integer SequenceNumber;
    @JsonProperty("AirItineraryPricingInfo")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.AirItineraryPricingInfo AirItineraryPricingInfo;
    @JsonProperty("TicketingInfo")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.TicketingInfo TicketingInfo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The AirItinerary
     */
    @JsonProperty("AirItinerary")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.AirItinerary getAirItinerary() {
        return AirItinerary;
    }

    /**
     * 
     * @param AirItinerary
     *     The AirItinerary
     */
    @JsonProperty("AirItinerary")
    public void setAirItinerary(com.sabre.api.sacs.rest.domain.instaflight_gen.AirItinerary AirItinerary) {
        this.AirItinerary = AirItinerary;
    }

    /**
     * 
     * @return
     *     The TPA_Extensions
     */
    @JsonProperty("TPA_Extensions")
    public TPA_Extensions_ getTPA_Extensions() {
        return TPA_Extensions;
    }

    /**
     * 
     * @param TPA_Extensions
     *     The TPA_Extensions
     */
    @JsonProperty("TPA_Extensions")
    public void setTPA_Extensions(TPA_Extensions_ TPA_Extensions) {
        this.TPA_Extensions = TPA_Extensions;
    }

    /**
     * 
     * @return
     *     The SequenceNumber
     */
    @JsonProperty("SequenceNumber")
    public Integer getSequenceNumber() {
        return SequenceNumber;
    }

    /**
     * 
     * @param SequenceNumber
     *     The SequenceNumber
     */
    @JsonProperty("SequenceNumber")
    public void setSequenceNumber(Integer SequenceNumber) {
        this.SequenceNumber = SequenceNumber;
    }

    /**
     * 
     * @return
     *     The AirItineraryPricingInfo
     */
    @JsonProperty("AirItineraryPricingInfo")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.AirItineraryPricingInfo getAirItineraryPricingInfo() {
        return AirItineraryPricingInfo;
    }

    /**
     * 
     * @param AirItineraryPricingInfo
     *     The AirItineraryPricingInfo
     */
    @JsonProperty("AirItineraryPricingInfo")
    public void setAirItineraryPricingInfo(com.sabre.api.sacs.rest.domain.instaflight_gen.AirItineraryPricingInfo AirItineraryPricingInfo) {
        this.AirItineraryPricingInfo = AirItineraryPricingInfo;
    }

    /**
     * 
     * @return
     *     The TicketingInfo
     */
    @JsonProperty("TicketingInfo")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.TicketingInfo getTicketingInfo() {
        return TicketingInfo;
    }

    /**
     * 
     * @param TicketingInfo
     *     The TicketingInfo
     */
    @JsonProperty("TicketingInfo")
    public void setTicketingInfo(com.sabre.api.sacs.rest.domain.instaflight_gen.TicketingInfo TicketingInfo) {
        this.TicketingInfo = TicketingInfo;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
