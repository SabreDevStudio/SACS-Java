
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
    "Code",
    "Quantity"
})
public class PassengerTypeQuantity {

    @JsonProperty("Code")
    private String Code;
    @JsonProperty("Quantity")
    private Integer Quantity;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Code
     */
    @JsonProperty("Code")
    public String getCode() {
        return Code;
    }

    /**
     * 
     * @param Code
     *     The Code
     */
    @JsonProperty("Code")
    public void setCode(String Code) {
        this.Code = Code;
    }

    public PassengerTypeQuantity withCode(String Code) {
        this.Code = Code;
        return this;
    }

    /**
     * 
     * @return
     *     The Quantity
     */
    @JsonProperty("Quantity")
    public Integer getQuantity() {
        return Quantity;
    }

    /**
     * 
     * @param Quantity
     *     The Quantity
     */
    @JsonProperty("Quantity")
    public void setQuantity(Integer Quantity) {
        this.Quantity = Quantity;
    }

    public PassengerTypeQuantity withQuantity(Integer Quantity) {
        this.Quantity = Quantity;
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

    public PassengerTypeQuantity withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
