
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
    "BelowMin",
    "Number"
})
public class SeatsRemaining {

    @JsonProperty("BelowMin")
    private Boolean BelowMin;
    @JsonProperty("Number")
    private Integer Number;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The BelowMin
     */
    @JsonProperty("BelowMin")
    public Boolean getBelowMin() {
        return BelowMin;
    }

    /**
     * 
     * @param BelowMin
     *     The BelowMin
     */
    @JsonProperty("BelowMin")
    public void setBelowMin(Boolean BelowMin) {
        this.BelowMin = BelowMin;
    }

    /**
     * 
     * @return
     *     The Number
     */
    @JsonProperty("Number")
    public Integer getNumber() {
        return Number;
    }

    /**
     * 
     * @param Number
     *     The Number
     */
    @JsonProperty("Number")
    public void setNumber(Integer Number) {
        this.Number = Number;
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
