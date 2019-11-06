
package com.sabre.api.sacs.rest.domain.bargainfindermax;

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
    "OTA_AirLowFareSearchRQ"
})
public class BargainFinderMaxRequest {

    @JsonProperty("OTA_AirLowFareSearchRQ")
    private com.sabre.api.sacs.rest.domain.bargainfindermax.OTAAirLowFareSearchRQ OTAAirLowFareSearchRQ;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The OTAAirLowFareSearchRQ
     */
    @JsonProperty("OTA_AirLowFareSearchRQ")
    public com.sabre.api.sacs.rest.domain.bargainfindermax.OTAAirLowFareSearchRQ getOTAAirLowFareSearchRQ() {
        return OTAAirLowFareSearchRQ;
    }

    /**
     * 
     * @param OTAAirLowFareSearchRQ
     *     The OTA_AirLowFareSearchRQ
     */
    @JsonProperty("OTA_AirLowFareSearchRQ")
    public void setOTAAirLowFareSearchRQ(com.sabre.api.sacs.rest.domain.bargainfindermax.OTAAirLowFareSearchRQ OTAAirLowFareSearchRQ) {
        this.OTAAirLowFareSearchRQ = OTAAirLowFareSearchRQ;
    }

    public BargainFinderMaxRequest withOTAAirLowFareSearchRQ(com.sabre.api.sacs.rest.domain.bargainfindermax.OTAAirLowFareSearchRQ OTAAirLowFareSearchRQ) {
        this.OTAAirLowFareSearchRQ = OTAAirLowFareSearchRQ;
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

    public BargainFinderMaxRequest withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
