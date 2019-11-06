
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
    "FareBasisCode"
})
public class FareBasisCodes {

    @JsonProperty("FareBasisCode")
    private List<com.sabre.api.sacs.rest.domain.instaflight_gen.FareBasisCode> FareBasisCode = new ArrayList<com.sabre.api.sacs.rest.domain.instaflight_gen.FareBasisCode>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The FareBasisCode
     */
    @JsonProperty("FareBasisCode")
    public List<com.sabre.api.sacs.rest.domain.instaflight_gen.FareBasisCode> getFareBasisCode() {
        return FareBasisCode;
    }

    /**
     * 
     * @param FareBasisCode
     *     The FareBasisCode
     */
    @JsonProperty("FareBasisCode")
    public void setFareBasisCode(List<com.sabre.api.sacs.rest.domain.instaflight_gen.FareBasisCode> FareBasisCode) {
        this.FareBasisCode = FareBasisCode;
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
