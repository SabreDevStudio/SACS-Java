
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
    "FareConstruction",
    "TotalFare",
    "Taxes",
    "BaseFare",
    "EquivFare"
})
public class PassengerFare {

    @JsonProperty("FareConstruction")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.FareConstruction FareConstruction;
    @JsonProperty("TotalFare")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.TotalFare TotalFare;
    @JsonProperty("Taxes")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.Taxes Taxes;
    @JsonProperty("BaseFare")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.BaseFare BaseFare;
    @JsonProperty("EquivFare")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.EquivFare EquivFare;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The FareConstruction
     */
    @JsonProperty("FareConstruction")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.FareConstruction getFareConstruction() {
        return FareConstruction;
    }

    /**
     * 
     * @param FareConstruction
     *     The FareConstruction
     */
    @JsonProperty("FareConstruction")
    public void setFareConstruction(com.sabre.api.sacs.rest.domain.instaflight_gen.FareConstruction FareConstruction) {
        this.FareConstruction = FareConstruction;
    }

    /**
     * 
     * @return
     *     The TotalFare
     */
    @JsonProperty("TotalFare")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.TotalFare getTotalFare() {
        return TotalFare;
    }

    /**
     * 
     * @param TotalFare
     *     The TotalFare
     */
    @JsonProperty("TotalFare")
    public void setTotalFare(com.sabre.api.sacs.rest.domain.instaflight_gen.TotalFare TotalFare) {
        this.TotalFare = TotalFare;
    }

    /**
     * 
     * @return
     *     The Taxes
     */
    @JsonProperty("Taxes")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.Taxes getTaxes() {
        return Taxes;
    }

    /**
     * 
     * @param Taxes
     *     The Taxes
     */
    @JsonProperty("Taxes")
    public void setTaxes(com.sabre.api.sacs.rest.domain.instaflight_gen.Taxes Taxes) {
        this.Taxes = Taxes;
    }

    /**
     * 
     * @return
     *     The BaseFare
     */
    @JsonProperty("BaseFare")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.BaseFare getBaseFare() {
        return BaseFare;
    }

    /**
     * 
     * @param BaseFare
     *     The BaseFare
     */
    @JsonProperty("BaseFare")
    public void setBaseFare(com.sabre.api.sacs.rest.domain.instaflight_gen.BaseFare BaseFare) {
        this.BaseFare = BaseFare;
    }

    /**
     * 
     * @return
     *     The EquivFare
     */
    @JsonProperty("EquivFare")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.EquivFare getEquivFare() {
        return EquivFare;
    }

    /**
     * 
     * @param EquivFare
     *     The EquivFare
     */
    @JsonProperty("EquivFare")
    public void setEquivFare(com.sabre.api.sacs.rest.domain.instaflight_gen.EquivFare EquivFare) {
        this.EquivFare = EquivFare;
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
