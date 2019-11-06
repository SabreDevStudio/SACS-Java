
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
    "DepartureAirport",
    "ArrivalAirport",
    "MarketingAirline",
    "ArrivalTimeZone",
    "TPA_Extensions",
    "StopQuantity",
    "ElapsedTime",
    "ResBookDesigCode",
    "MarriageGrp",
    "Equipment",
    "DepartureDateTime",
    "ArrivalDateTime",
    "FlightNumber",
    "OperatingAirline",
    "DepartureTimeZone"
})
public class FlightSegment {

    @JsonProperty("DepartureAirport")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.DepartureAirport DepartureAirport;
    @JsonProperty("ArrivalAirport")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.ArrivalAirport ArrivalAirport;
    @JsonProperty("MarketingAirline")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.MarketingAirline MarketingAirline;
    @JsonProperty("ArrivalTimeZone")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.ArrivalTimeZone ArrivalTimeZone;
    @JsonProperty("TPA_Extensions")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.TPA_Extensions TPA_Extensions;
    @JsonProperty("StopQuantity")
    private Integer StopQuantity;
    @JsonProperty("ElapsedTime")
    private Integer ElapsedTime;
    @JsonProperty("ResBookDesigCode")
    private String ResBookDesigCode;
    @JsonProperty("MarriageGrp")
    private String MarriageGrp;
    @JsonProperty("Equipment")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.Equipment Equipment;
    @JsonProperty("DepartureDateTime")
    private String DepartureDateTime;
    @JsonProperty("ArrivalDateTime")
    private String ArrivalDateTime;
    @JsonProperty("FlightNumber")
    private Integer FlightNumber;
    @JsonProperty("OperatingAirline")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.OperatingAirline OperatingAirline;
    @JsonProperty("DepartureTimeZone")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.DepartureTimeZone DepartureTimeZone;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The DepartureAirport
     */
    @JsonProperty("DepartureAirport")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.DepartureAirport getDepartureAirport() {
        return DepartureAirport;
    }

    /**
     * 
     * @param DepartureAirport
     *     The DepartureAirport
     */
    @JsonProperty("DepartureAirport")
    public void setDepartureAirport(com.sabre.api.sacs.rest.domain.instaflight_gen.DepartureAirport DepartureAirport) {
        this.DepartureAirport = DepartureAirport;
    }

    /**
     * 
     * @return
     *     The ArrivalAirport
     */
    @JsonProperty("ArrivalAirport")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.ArrivalAirport getArrivalAirport() {
        return ArrivalAirport;
    }

    /**
     * 
     * @param ArrivalAirport
     *     The ArrivalAirport
     */
    @JsonProperty("ArrivalAirport")
    public void setArrivalAirport(com.sabre.api.sacs.rest.domain.instaflight_gen.ArrivalAirport ArrivalAirport) {
        this.ArrivalAirport = ArrivalAirport;
    }

    /**
     * 
     * @return
     *     The MarketingAirline
     */
    @JsonProperty("MarketingAirline")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.MarketingAirline getMarketingAirline() {
        return MarketingAirline;
    }

    /**
     * 
     * @param MarketingAirline
     *     The MarketingAirline
     */
    @JsonProperty("MarketingAirline")
    public void setMarketingAirline(com.sabre.api.sacs.rest.domain.instaflight_gen.MarketingAirline MarketingAirline) {
        this.MarketingAirline = MarketingAirline;
    }

    /**
     * 
     * @return
     *     The ArrivalTimeZone
     */
    @JsonProperty("ArrivalTimeZone")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.ArrivalTimeZone getArrivalTimeZone() {
        return ArrivalTimeZone;
    }

    /**
     * 
     * @param ArrivalTimeZone
     *     The ArrivalTimeZone
     */
    @JsonProperty("ArrivalTimeZone")
    public void setArrivalTimeZone(com.sabre.api.sacs.rest.domain.instaflight_gen.ArrivalTimeZone ArrivalTimeZone) {
        this.ArrivalTimeZone = ArrivalTimeZone;
    }

    /**
     * 
     * @return
     *     The TPA_Extensions
     */
    @JsonProperty("TPA_Extensions")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.TPA_Extensions getTPA_Extensions() {
        return TPA_Extensions;
    }

    /**
     * 
     * @param TPA_Extensions
     *     The TPA_Extensions
     */
    @JsonProperty("TPA_Extensions")
    public void setTPA_Extensions(com.sabre.api.sacs.rest.domain.instaflight_gen.TPA_Extensions TPA_Extensions) {
        this.TPA_Extensions = TPA_Extensions;
    }

    /**
     * 
     * @return
     *     The StopQuantity
     */
    @JsonProperty("StopQuantity")
    public Integer getStopQuantity() {
        return StopQuantity;
    }

    /**
     * 
     * @param StopQuantity
     *     The StopQuantity
     */
    @JsonProperty("StopQuantity")
    public void setStopQuantity(Integer StopQuantity) {
        this.StopQuantity = StopQuantity;
    }

    /**
     * 
     * @return
     *     The ElapsedTime
     */
    @JsonProperty("ElapsedTime")
    public Integer getElapsedTime() {
        return ElapsedTime;
    }

    /**
     * 
     * @param ElapsedTime
     *     The ElapsedTime
     */
    @JsonProperty("ElapsedTime")
    public void setElapsedTime(Integer ElapsedTime) {
        this.ElapsedTime = ElapsedTime;
    }

    /**
     * 
     * @return
     *     The ResBookDesigCode
     */
    @JsonProperty("ResBookDesigCode")
    public String getResBookDesigCode() {
        return ResBookDesigCode;
    }

    /**
     * 
     * @param ResBookDesigCode
     *     The ResBookDesigCode
     */
    @JsonProperty("ResBookDesigCode")
    public void setResBookDesigCode(String ResBookDesigCode) {
        this.ResBookDesigCode = ResBookDesigCode;
    }

    /**
     * 
     * @return
     *     The MarriageGrp
     */
    @JsonProperty("MarriageGrp")
    public String getMarriageGrp() {
        return MarriageGrp;
    }

    /**
     * 
     * @param MarriageGrp
     *     The MarriageGrp
     */
    @JsonProperty("MarriageGrp")
    public void setMarriageGrp(String MarriageGrp) {
        this.MarriageGrp = MarriageGrp;
    }

    /**
     * 
     * @return
     *     The Equipment
     */
    @JsonProperty("Equipment")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.Equipment getEquipment() {
        return Equipment;
    }

    /**
     * 
     * @param Equipment
     *     The Equipment
     */
    @JsonProperty("Equipment")
    public void setEquipment(com.sabre.api.sacs.rest.domain.instaflight_gen.Equipment Equipment) {
        this.Equipment = Equipment;
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
     *     The ArrivalDateTime
     */
    @JsonProperty("ArrivalDateTime")
    public String getArrivalDateTime() {
        return ArrivalDateTime;
    }

    /**
     * 
     * @param ArrivalDateTime
     *     The ArrivalDateTime
     */
    @JsonProperty("ArrivalDateTime")
    public void setArrivalDateTime(String ArrivalDateTime) {
        this.ArrivalDateTime = ArrivalDateTime;
    }

    /**
     * 
     * @return
     *     The FlightNumber
     */
    @JsonProperty("FlightNumber")
    public Integer getFlightNumber() {
        return FlightNumber;
    }

    /**
     * 
     * @param FlightNumber
     *     The FlightNumber
     */
    @JsonProperty("FlightNumber")
    public void setFlightNumber(Integer FlightNumber) {
        this.FlightNumber = FlightNumber;
    }

    /**
     * 
     * @return
     *     The OperatingAirline
     */
    @JsonProperty("OperatingAirline")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.OperatingAirline getOperatingAirline() {
        return OperatingAirline;
    }

    /**
     * 
     * @param OperatingAirline
     *     The OperatingAirline
     */
    @JsonProperty("OperatingAirline")
    public void setOperatingAirline(com.sabre.api.sacs.rest.domain.instaflight_gen.OperatingAirline OperatingAirline) {
        this.OperatingAirline = OperatingAirline;
    }

    /**
     * 
     * @return
     *     The DepartureTimeZone
     */
    @JsonProperty("DepartureTimeZone")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.DepartureTimeZone getDepartureTimeZone() {
        return DepartureTimeZone;
    }

    /**
     * 
     * @param DepartureTimeZone
     *     The DepartureTimeZone
     */
    @JsonProperty("DepartureTimeZone")
    public void setDepartureTimeZone(com.sabre.api.sacs.rest.domain.instaflight_gen.DepartureTimeZone DepartureTimeZone) {
        this.DepartureTimeZone = DepartureTimeZone;
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
