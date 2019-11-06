
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
    "FareInfo"
})
public class FareInfos {

    @JsonProperty("FareInfo")
    private List<com.sabre.api.sacs.rest.domain.instaflight_gen.FareInfo> FareInfo = new ArrayList<com.sabre.api.sacs.rest.domain.instaflight_gen.FareInfo>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The FareInfo
     */
    @JsonProperty("FareInfo")
    public List<com.sabre.api.sacs.rest.domain.instaflight_gen.FareInfo> getFareInfo() {
        return FareInfo;
    }

    /**
     * 
     * @param FareInfo
     *     The FareInfo
     */
    @JsonProperty("FareInfo")
    public void setFareInfo(List<com.sabre.api.sacs.rest.domain.instaflight_gen.FareInfo> FareInfo) {
        this.FareInfo = FareInfo;
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
