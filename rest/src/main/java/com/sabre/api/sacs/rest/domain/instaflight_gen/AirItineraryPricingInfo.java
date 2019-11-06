
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
    "PTC_FareBreakdowns",
    "FareInfos",
    "TPA_Extensions",
    "ItinTotalFare"
})
public class AirItineraryPricingInfo {

    @JsonProperty("PTC_FareBreakdowns")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.PTC_FareBreakdowns PTC_FareBreakdowns;
    @JsonProperty("FareInfos")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.FareInfos FareInfos;
    @JsonProperty("TPA_Extensions")
    private TPA_Extensions___ TPA_Extensions;
    @JsonProperty("ItinTotalFare")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.ItinTotalFare ItinTotalFare;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The PTC_FareBreakdowns
     */
    @JsonProperty("PTC_FareBreakdowns")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.PTC_FareBreakdowns getPTC_FareBreakdowns() {
        return PTC_FareBreakdowns;
    }

    /**
     * 
     * @param PTC_FareBreakdowns
     *     The PTC_FareBreakdowns
     */
    @JsonProperty("PTC_FareBreakdowns")
    public void setPTC_FareBreakdowns(com.sabre.api.sacs.rest.domain.instaflight_gen.PTC_FareBreakdowns PTC_FareBreakdowns) {
        this.PTC_FareBreakdowns = PTC_FareBreakdowns;
    }

    /**
     * 
     * @return
     *     The FareInfos
     */
    @JsonProperty("FareInfos")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.FareInfos getFareInfos() {
        return FareInfos;
    }

    /**
     * 
     * @param FareInfos
     *     The FareInfos
     */
    @JsonProperty("FareInfos")
    public void setFareInfos(com.sabre.api.sacs.rest.domain.instaflight_gen.FareInfos FareInfos) {
        this.FareInfos = FareInfos;
    }

    /**
     * 
     * @return
     *     The TPA_Extensions
     */
    @JsonProperty("TPA_Extensions")
    public TPA_Extensions___ getTPA_Extensions() {
        return TPA_Extensions;
    }

    /**
     * 
     * @param TPA_Extensions
     *     The TPA_Extensions
     */
    @JsonProperty("TPA_Extensions")
    public void setTPA_Extensions(TPA_Extensions___ TPA_Extensions) {
        this.TPA_Extensions = TPA_Extensions;
    }

    /**
     * 
     * @return
     *     The ItinTotalFare
     */
    @JsonProperty("ItinTotalFare")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.ItinTotalFare getItinTotalFare() {
        return ItinTotalFare;
    }

    /**
     * 
     * @param ItinTotalFare
     *     The ItinTotalFare
     */
    @JsonProperty("ItinTotalFare")
    public void setItinTotalFare(com.sabre.api.sacs.rest.domain.instaflight_gen.ItinTotalFare ItinTotalFare) {
        this.ItinTotalFare = ItinTotalFare;
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
