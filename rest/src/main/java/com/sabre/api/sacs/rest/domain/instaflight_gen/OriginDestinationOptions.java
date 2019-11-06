
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
    "OriginDestinationOption"
})
public class OriginDestinationOptions {

    @JsonProperty("OriginDestinationOption")
    private List<com.sabre.api.sacs.rest.domain.instaflight_gen.OriginDestinationOption> OriginDestinationOption = new ArrayList<com.sabre.api.sacs.rest.domain.instaflight_gen.OriginDestinationOption>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The OriginDestinationOption
     */
    @JsonProperty("OriginDestinationOption")
    public List<com.sabre.api.sacs.rest.domain.instaflight_gen.OriginDestinationOption> getOriginDestinationOption() {
        return OriginDestinationOption;
    }

    /**
     * 
     * @param OriginDestinationOption
     *     The OriginDestinationOption
     */
    @JsonProperty("OriginDestinationOption")
    public void setOriginDestinationOption(List<com.sabre.api.sacs.rest.domain.instaflight_gen.OriginDestinationOption> OriginDestinationOption) {
        this.OriginDestinationOption = OriginDestinationOption;
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
