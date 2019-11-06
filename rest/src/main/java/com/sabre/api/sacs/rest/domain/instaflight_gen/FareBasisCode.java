
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
    "BookingCode",
    "DepartureAirportCode",
    "ArrivalAirportCode",
    "content",
    "AvailabilityBreak"
})
public class FareBasisCode {

    @JsonProperty("BookingCode")
    private String BookingCode;
    @JsonProperty("DepartureAirportCode")
    private String DepartureAirportCode;
    @JsonProperty("ArrivalAirportCode")
    private String ArrivalAirportCode;
    @JsonProperty("content")
    private String content;
    @JsonProperty("AvailabilityBreak")
    private Boolean AvailabilityBreak;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The BookingCode
     */
    @JsonProperty("BookingCode")
    public String getBookingCode() {
        return BookingCode;
    }

    /**
     * 
     * @param BookingCode
     *     The BookingCode
     */
    @JsonProperty("BookingCode")
    public void setBookingCode(String BookingCode) {
        this.BookingCode = BookingCode;
    }

    /**
     * 
     * @return
     *     The DepartureAirportCode
     */
    @JsonProperty("DepartureAirportCode")
    public String getDepartureAirportCode() {
        return DepartureAirportCode;
    }

    /**
     * 
     * @param DepartureAirportCode
     *     The DepartureAirportCode
     */
    @JsonProperty("DepartureAirportCode")
    public void setDepartureAirportCode(String DepartureAirportCode) {
        this.DepartureAirportCode = DepartureAirportCode;
    }

    /**
     * 
     * @return
     *     The ArrivalAirportCode
     */
    @JsonProperty("ArrivalAirportCode")
    public String getArrivalAirportCode() {
        return ArrivalAirportCode;
    }

    /**
     * 
     * @param ArrivalAirportCode
     *     The ArrivalAirportCode
     */
    @JsonProperty("ArrivalAirportCode")
    public void setArrivalAirportCode(String ArrivalAirportCode) {
        this.ArrivalAirportCode = ArrivalAirportCode;
    }

    /**
     * 
     * @return
     *     The content
     */
    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     * @return
     *     The AvailabilityBreak
     */
    @JsonProperty("AvailabilityBreak")
    public Boolean getAvailabilityBreak() {
        return AvailabilityBreak;
    }

    /**
     * 
     * @param AvailabilityBreak
     *     The AvailabilityBreak
     */
    @JsonProperty("AvailabilityBreak")
    public void setAvailabilityBreak(Boolean AvailabilityBreak) {
        this.AvailabilityBreak = AvailabilityBreak;
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
