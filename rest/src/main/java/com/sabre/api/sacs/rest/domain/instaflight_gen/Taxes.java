
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
    "TotalTax",
    "Tax"
})
public class Taxes {

    @JsonProperty("TotalTax")
    private com.sabre.api.sacs.rest.domain.instaflight_gen.TotalTax TotalTax;
    @JsonProperty("Tax")
    private List<com.sabre.api.sacs.rest.domain.instaflight_gen.Tax> Tax = new ArrayList<com.sabre.api.sacs.rest.domain.instaflight_gen.Tax>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The TotalTax
     */
    @JsonProperty("TotalTax")
    public com.sabre.api.sacs.rest.domain.instaflight_gen.TotalTax getTotalTax() {
        return TotalTax;
    }

    /**
     * 
     * @param TotalTax
     *     The TotalTax
     */
    @JsonProperty("TotalTax")
    public void setTotalTax(com.sabre.api.sacs.rest.domain.instaflight_gen.TotalTax TotalTax) {
        this.TotalTax = TotalTax;
    }

    /**
     * 
     * @return
     *     The Tax
     */
    @JsonProperty("Tax")
    public List<com.sabre.api.sacs.rest.domain.instaflight_gen.Tax> getTax() {
        return Tax;
    }

    /**
     * 
     * @param Tax
     *     The Tax
     */
    @JsonProperty("Tax")
    public void setTax(List<com.sabre.api.sacs.rest.domain.instaflight_gen.Tax> Tax) {
        this.Tax = Tax;
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
