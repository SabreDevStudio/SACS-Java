
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
    "DivideInParty"
})
public class TPA_Extensions___ {

    @JsonProperty("DivideInParty")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.DivideInParty DivideInParty;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The DivideInParty
     */
    @JsonProperty("DivideInParty")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.DivideInParty getDivideInParty() {
        return DivideInParty;
    }

    /**
     * 
     * @param DivideInParty
     *     The DivideInParty
     */
    @JsonProperty("DivideInParty")
    public void setDivideInParty(com.sabre.api.sacs.rest.domain.instaflight_gen.DivideInParty DivideInParty) {
        this.DivideInParty = DivideInParty;
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
