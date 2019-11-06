
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
    "CurrencyCode",
    "DecimalPlaces",
    "Amount"
})
public class BaseFare_ {

    @JsonProperty("CurrencyCode")
    private String CurrencyCode;
    @JsonProperty("DecimalPlaces")
    private Integer DecimalPlaces;
    @JsonProperty("Amount")
    private Double Amount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The CurrencyCode
     */
    @JsonProperty("CurrencyCode")
    public String getCurrencyCode() {
        return CurrencyCode;
    }

    /**
     * 
     * @param CurrencyCode
     *     The CurrencyCode
     */
    @JsonProperty("CurrencyCode")
    public void setCurrencyCode(String CurrencyCode) {
        this.CurrencyCode = CurrencyCode;
    }

    /**
     * 
     * @return
     *     The DecimalPlaces
     */
    @JsonProperty("DecimalPlaces")
    public Integer getDecimalPlaces() {
        return DecimalPlaces;
    }

    /**
     * 
     * @param DecimalPlaces
     *     The DecimalPlaces
     */
    @JsonProperty("DecimalPlaces")
    public void setDecimalPlaces(Integer DecimalPlaces) {
        this.DecimalPlaces = DecimalPlaces;
    }

    /**
     * 
     * @return
     *     The Amount
     */
    @JsonProperty("Amount")
    public Double getAmount() {
        return Amount;
    }

    /**
     * 
     * @param Amount
     *     The Amount
     */
    @JsonProperty("Amount")
    public void setAmount(Double Amount) {
        this.Amount = Amount;
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
