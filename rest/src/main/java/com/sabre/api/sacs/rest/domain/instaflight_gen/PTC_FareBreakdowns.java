
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
    "PTC_FareBreakdown"
})
public class PTC_FareBreakdowns {

    @JsonProperty("PTC_FareBreakdown")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.PTC_FareBreakdown PTC_FareBreakdown;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The PTC_FareBreakdown
     */
    @JsonProperty("PTC_FareBreakdown")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.PTC_FareBreakdown getPTC_FareBreakdown() {
        return PTC_FareBreakdown;
    }

    /**
     * 
     * @param PTC_FareBreakdown
     *     The PTC_FareBreakdown
     */
    @JsonProperty("PTC_FareBreakdown")
    public void setPTC_FareBreakdown(com.sabre.api.sacs.rest.domain.instaflight_gen.PTC_FareBreakdown PTC_FareBreakdown) {
        this.PTC_FareBreakdown = PTC_FareBreakdown;
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
