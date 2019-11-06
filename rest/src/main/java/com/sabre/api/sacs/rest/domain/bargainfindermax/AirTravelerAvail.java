
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
    "PassengerTypeQuantity"
})
public class AirTravelerAvail {

    @JsonProperty("PassengerTypeQuantity")
    private List<com.sabre.api.sacs.rest.domain.bargainfindermax.PassengerTypeQuantity> PassengerTypeQuantity = new ArrayList<com.sabre.api.sacs.rest.domain.bargainfindermax.PassengerTypeQuantity>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The PassengerTypeQuantity
     */
    @JsonProperty("PassengerTypeQuantity")
    public List<com.sabre.api.sacs.rest.domain.bargainfindermax.PassengerTypeQuantity> getPassengerTypeQuantity() {
        return PassengerTypeQuantity;
    }

    /**
     * 
     * @param PassengerTypeQuantity
     *     The PassengerTypeQuantity
     */
    @JsonProperty("PassengerTypeQuantity")
    public void setPassengerTypeQuantity(List<com.sabre.api.sacs.rest.domain.bargainfindermax.PassengerTypeQuantity> PassengerTypeQuantity) {
        this.PassengerTypeQuantity = PassengerTypeQuantity;
    }

    public AirTravelerAvail withPassengerTypeQuantity(List<com.sabre.api.sacs.rest.domain.bargainfindermax.PassengerTypeQuantity> PassengerTypeQuantity) {
        this.PassengerTypeQuantity = PassengerTypeQuantity;
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

    public AirTravelerAvail withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
