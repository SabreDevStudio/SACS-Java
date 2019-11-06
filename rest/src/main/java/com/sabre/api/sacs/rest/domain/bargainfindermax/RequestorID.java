
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
    "CompanyName",
    "ID",
    "Type"
})
public class RequestorID {

    @JsonProperty("CompanyName")
    private com.sabre.api.sacs.rest.domain.bargainfindermax.CompanyName CompanyName;
    @JsonProperty("ID")
    private String ID;
    @JsonProperty("Type")
    private String Type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The CompanyName
     */
    @JsonProperty("CompanyName")
    public com.sabre.api.sacs.rest.domain.bargainfindermax.CompanyName getCompanyName() {
        return CompanyName;
    }

    /**
     * 
     * @param CompanyName
     *     The CompanyName
     */
    @JsonProperty("CompanyName")
    public void setCompanyName(com.sabre.api.sacs.rest.domain.bargainfindermax.CompanyName CompanyName) {
        this.CompanyName = CompanyName;
    }

    public RequestorID withCompanyName(com.sabre.api.sacs.rest.domain.bargainfindermax.CompanyName CompanyName) {
        this.CompanyName = CompanyName;
        return this;
    }

    /**
     * 
     * @return
     *     The ID
     */
    @JsonProperty("ID")
    public String getID() {
        return ID;
    }

    /**
     * 
     * @param ID
     *     The ID
     */
    @JsonProperty("ID")
    public void setID(String ID) {
        this.ID = ID;
    }

    public RequestorID withID(String ID) {
        this.ID = ID;
        return this;
    }

    /**
     * 
     * @return
     *     The Type
     */
    @JsonProperty("Type")
    public String getType() {
        return Type;
    }

    /**
     * 
     * @param Type
     *     The Type
     */
    @JsonProperty("Type")
    public void setType(String Type) {
        this.Type = Type;
    }

    public RequestorID withType(String Type) {
        this.Type = Type;
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

    public RequestorID withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
