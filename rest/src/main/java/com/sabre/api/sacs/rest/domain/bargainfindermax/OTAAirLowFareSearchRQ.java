
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
    "OriginDestinationInformation",
    "POS",
    "TPA_Extensions",
    "TravelerInfoSummary"
})
public class OTAAirLowFareSearchRQ {

    @JsonProperty("OriginDestinationInformation")
    private List<com.sabre.api.sacs.rest.domain.bargainfindermax.OriginDestinationInformation> OriginDestinationInformation = new ArrayList<com.sabre.api.sacs.rest.domain.bargainfindermax.OriginDestinationInformation>();
    @JsonProperty("POS")
    private com.sabre.api.sacs.rest.domain.bargainfindermax.POS POS;
    @JsonProperty("TPA_Extensions")
    private com.sabre.api.sacs.rest.domain.bargainfindermax.TPAExtensions TPAExtensions;
    @JsonProperty("TravelerInfoSummary")
    private com.sabre.api.sacs.rest.domain.bargainfindermax.TravelerInfoSummary TravelerInfoSummary;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The OriginDestinationInformation
     */
    @JsonProperty("OriginDestinationInformation")
    public List<com.sabre.api.sacs.rest.domain.bargainfindermax.OriginDestinationInformation> getOriginDestinationInformation() {
        return OriginDestinationInformation;
    }

    /**
     * 
     * @param OriginDestinationInformation
     *     The OriginDestinationInformation
     */
    @JsonProperty("OriginDestinationInformation")
    public void setOriginDestinationInformation(List<com.sabre.api.sacs.rest.domain.bargainfindermax.OriginDestinationInformation> OriginDestinationInformation) {
        this.OriginDestinationInformation = OriginDestinationInformation;
    }

    public OTAAirLowFareSearchRQ withOriginDestinationInformation(List<com.sabre.api.sacs.rest.domain.bargainfindermax.OriginDestinationInformation> OriginDestinationInformation) {
        this.OriginDestinationInformation = OriginDestinationInformation;
        return this;
    }

    /**
     * 
     * @return
     *     The POS
     */
    @JsonProperty("POS")
    public com.sabre.api.sacs.rest.domain.bargainfindermax.POS getPOS() {
        return POS;
    }

    /**
     * 
     * @param POS
     *     The POS
     */
    @JsonProperty("POS")
    public void setPOS(com.sabre.api.sacs.rest.domain.bargainfindermax.POS POS) {
        this.POS = POS;
    }

    public OTAAirLowFareSearchRQ withPOS(com.sabre.api.sacs.rest.domain.bargainfindermax.POS POS) {
        this.POS = POS;
        return this;
    }

    /**
     * 
     * @return
     *     The TPAExtensions
     */
    @JsonProperty("TPA_Extensions")
    public com.sabre.api.sacs.rest.domain.bargainfindermax.TPAExtensions getTPAExtensions() {
        return TPAExtensions;
    }

    /**
     * 
     * @param TPAExtensions
     *     The TPA_Extensions
     */
    @JsonProperty("TPA_Extensions")
    public void setTPAExtensions(com.sabre.api.sacs.rest.domain.bargainfindermax.TPAExtensions TPAExtensions) {
        this.TPAExtensions = TPAExtensions;
    }

    public OTAAirLowFareSearchRQ withTPAExtensions(com.sabre.api.sacs.rest.domain.bargainfindermax.TPAExtensions TPAExtensions) {
        this.TPAExtensions = TPAExtensions;
        return this;
    }

    /**
     * 
     * @return
     *     The TravelerInfoSummary
     */
    @JsonProperty("TravelerInfoSummary")
    public com.sabre.api.sacs.rest.domain.bargainfindermax.TravelerInfoSummary getTravelerInfoSummary() {
        return TravelerInfoSummary;
    }

    /**
     * 
     * @param TravelerInfoSummary
     *     The TravelerInfoSummary
     */
    @JsonProperty("TravelerInfoSummary")
    public void setTravelerInfoSummary(com.sabre.api.sacs.rest.domain.bargainfindermax.TravelerInfoSummary TravelerInfoSummary) {
        this.TravelerInfoSummary = TravelerInfoSummary;
    }

    public OTAAirLowFareSearchRQ withTravelerInfoSummary(com.sabre.api.sacs.rest.domain.bargainfindermax.TravelerInfoSummary TravelerInfoSummary) {
        this.TravelerInfoSummary = TravelerInfoSummary;
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

    public OTAAirLowFareSearchRQ withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
