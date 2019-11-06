
package com.sabre.api.sacs.rest.domain.instaflight_gen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "PricedItineraries",
    "ReturnDateTime",
    "DepartureDateTime",
    "DestinationLocation",
    "OriginLocation",
    "Links"
})
public class InstaFlightResponse {

    @JsonProperty("PricedItineraries")
    private List<PricedItinerary> PricedItineraries = new ArrayList<PricedItinerary>();
    @JsonProperty("ReturnDateTime")
    private String ReturnDateTime;
    @JsonProperty("DepartureDateTime")
    private String DepartureDateTime;
    @JsonProperty("DestinationLocation")
    private String DestinationLocation;
    @JsonProperty("OriginLocation")
    private String OriginLocation;
    @JsonProperty("Links")
    private List<Link> Links = new ArrayList<Link>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The PricedItineraries
     */
    @JsonProperty("PricedItineraries")
    public List<PricedItinerary> getPricedItineraries() {
        return PricedItineraries;
    }

    /**
     * 
     * @param PricedItineraries
     *     The PricedItineraries
     */
    @JsonProperty("PricedItineraries")
    public void setPricedItineraries(List<PricedItinerary> PricedItineraries) {
        this.PricedItineraries = PricedItineraries;
    }

    /**
     * 
     * @return
     *     The ReturnDateTime
     */
    @JsonProperty("ReturnDateTime")
    public String getReturnDateTime() {
        return ReturnDateTime;
    }

    /**
     * 
     * @param ReturnDateTime
     *     The ReturnDateTime
     */
    @JsonProperty("ReturnDateTime")
    public void setReturnDateTime(String ReturnDateTime) {
        this.ReturnDateTime = ReturnDateTime;
    }

    /**
     * 
     * @return
     *     The DepartureDateTime
     */
    @JsonProperty("DepartureDateTime")
    public String getDepartureDateTime() {
        return DepartureDateTime;
    }

    /**
     * 
     * @param DepartureDateTime
     *     The DepartureDateTime
     */
    @JsonProperty("DepartureDateTime")
    public void setDepartureDateTime(String DepartureDateTime) {
        this.DepartureDateTime = DepartureDateTime;
    }

    /**
     * 
     * @return
     *     The DestinationLocation
     */
    @JsonProperty("DestinationLocation")
    public String getDestinationLocation() {
        return DestinationLocation;
    }

    /**
     * 
     * @param DestinationLocation
     *     The DestinationLocation
     */
    @JsonProperty("DestinationLocation")
    public void setDestinationLocation(String DestinationLocation) {
        this.DestinationLocation = DestinationLocation;
    }

    /**
     * 
     * @return
     *     The OriginLocation
     */
    @JsonProperty("OriginLocation")
    public String getOriginLocation() {
        return OriginLocation;
    }

    /**
     * 
     * @param OriginLocation
     *     The OriginLocation
     */
    @JsonProperty("OriginLocation")
    public void setOriginLocation(String OriginLocation) {
        this.OriginLocation = OriginLocation;
    }

    /**
     * 
     * @return
     *     The Links
     */
    @JsonProperty("Links")
    public List<Link> getLinks() {
        return Links;
    }

    /**
     * 
     * @param Links
     *     The Links
     */
    @JsonProperty("Links")
    public void setLinks(List<Link> Links) {
        this.Links = Links;
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
