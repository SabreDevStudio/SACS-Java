
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
    "ValidatingCarrier"
})
public class TPA_Extensions_ {

    @JsonProperty("ValidatingCarrier")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.ValidatingCarrier ValidatingCarrier;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The ValidatingCarrier
     */
    @JsonProperty("ValidatingCarrier")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.ValidatingCarrier getValidatingCarrier() {
        return ValidatingCarrier;
    }

    /**
     * 
     * @param ValidatingCarrier
     *     The ValidatingCarrier
     */
    @JsonProperty("ValidatingCarrier")
    public void setValidatingCarrier(com.sabre.api.sacs.rest.domain.instaflight_gen.ValidatingCarrier ValidatingCarrier) {
        this.ValidatingCarrier = ValidatingCarrier;
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
