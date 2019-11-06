
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
    "TPA_Extensions",
    "FareReference"
})
public class FareInfo {

    @JsonProperty("TPA_Extensions")
    private TPA_Extensions__ TPA_Extensions;
    @JsonProperty("FareReference")
    private String FareReference;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The TPA_Extensions
     */
    @JsonProperty("TPA_Extensions")
    public TPA_Extensions__ getTPA_Extensions() {
        return TPA_Extensions;
    }

    /**
     * 
     * @param TPA_Extensions
     *     The TPA_Extensions
     */
    @JsonProperty("TPA_Extensions")
    public void setTPA_Extensions(TPA_Extensions__ TPA_Extensions) {
        this.TPA_Extensions = TPA_Extensions;
    }

    /**
     * 
     * @return
     *     The FareReference
     */
    @JsonProperty("FareReference")
    public String getFareReference() {
        return FareReference;
    }

    /**
     * 
     * @param FareReference
     *     The FareReference
     */
    @JsonProperty("FareReference")
    public void setFareReference(String FareReference) {
        this.FareReference = FareReference;
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
