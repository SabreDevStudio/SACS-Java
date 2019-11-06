
package com.sabre.api.sacs.rest.domain.bargainfindermax;

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
    "Source"
})
public class POS {

    @JsonProperty("Source")
    private List<com.sabre.api.sacs.rest.domain.bargainfindermax.Source> Source = new ArrayList<com.sabre.api.sacs.rest.domain.bargainfindermax.Source>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Source
     */
    @JsonProperty("Source")
    public List<com.sabre.api.sacs.rest.domain.bargainfindermax.Source> getSource() {
        return Source;
    }

    /**
     * 
     * @param Source
     *     The Source
     */
    @JsonProperty("Source")
    public void setSource(List<com.sabre.api.sacs.rest.domain.bargainfindermax.Source> Source) {
        this.Source = Source;
    }

    public POS withSource(List<com.sabre.api.sacs.rest.domain.bargainfindermax.Source> Source) {
        this.Source = Source;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public POS withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
