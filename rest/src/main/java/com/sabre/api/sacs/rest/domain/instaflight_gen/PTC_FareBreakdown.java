
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
    "FareBasisCodes",
    "PassengerTypeQuantity",
    "PassengerFare"
})
public class PTC_FareBreakdown {

    @JsonProperty("FareBasisCodes")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.FareBasisCodes FareBasisCodes;
    @JsonProperty("PassengerTypeQuantity")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.PassengerTypeQuantity PassengerTypeQuantity;
    @JsonProperty("PassengerFare")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.PassengerFare PassengerFare;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The FareBasisCodes
     */
    @JsonProperty("FareBasisCodes")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.FareBasisCodes getFareBasisCodes() {
        return FareBasisCodes;
    }

    /**
     * 
     * @param FareBasisCodes
     *     The FareBasisCodes
     */
    @JsonProperty("FareBasisCodes")
    public void setFareBasisCodes(com.sabre.api.sacs.rest.domain.instaflight_gen.FareBasisCodes FareBasisCodes) {
        this.FareBasisCodes = FareBasisCodes;
    }

    /**
     * 
     * @return
     *     The PassengerTypeQuantity
     */
    @JsonProperty("PassengerTypeQuantity")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.PassengerTypeQuantity getPassengerTypeQuantity() {
        return PassengerTypeQuantity;
    }

    /**
     * 
     * @param PassengerTypeQuantity
     *     The PassengerTypeQuantity
     */
    @JsonProperty("PassengerTypeQuantity")
    public void setPassengerTypeQuantity(com.sabre.api.sacs.rest.domain.instaflight_gen.PassengerTypeQuantity PassengerTypeQuantity) {
        this.PassengerTypeQuantity = PassengerTypeQuantity;
    }

    /**
     * 
     * @return
     *     The PassengerFare
     */
    @JsonProperty("PassengerFare")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.PassengerFare getPassengerFare() {
        return PassengerFare;
    }

    /**
     * 
     * @param PassengerFare
     *     The PassengerFare
     */
    @JsonProperty("PassengerFare")
    public void setPassengerFare(com.sabre.api.sacs.rest.domain.instaflight_gen.PassengerFare PassengerFare) {
        this.PassengerFare = PassengerFare;
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
