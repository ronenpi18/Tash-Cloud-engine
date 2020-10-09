package com.ofek324.Entities.Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "serviceName",
        "flow",
        "iaAvailable"
})
public class Flow {

    @JsonProperty("serviceName")
    private String serviceName;
    @JsonProperty("flow")
    private List<SequenceFlow> flow = new ArrayList<SequenceFlow>();
    @JsonProperty("iaAvailable")
    private Boolean iaAvailable;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Flow() {
    }

    /**
     *
     * @param iaAvailable
     * @param serviceName
     * @param flow
     */
    public Flow(String serviceName, List<SequenceFlow> flow, Boolean iaAvailable) {
        super();
        this.serviceName = serviceName;
        this.flow = flow;
        this.iaAvailable = iaAvailable;
    }

    @JsonProperty("serviceName")
    public String getServiceName() {
        return serviceName;
    }

    @JsonProperty("serviceName")
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @JsonProperty("flow")
    public List<SequenceFlow> getFlow() {
        return flow;
    }

    @JsonProperty("flow")
    public void setFlow(List<SequenceFlow> flow) {
        this.flow = flow;
    }

    @JsonProperty("iaAvailable")
    public Boolean getIaAvailable() {
        return iaAvailable;
    }

    @JsonProperty("iaAvailable")
    public void setIaAvailable(Boolean iaAvailable) {
        this.iaAvailable = iaAvailable;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("serviceName", serviceName).append("flow", flow).append("iaAvailable", iaAvailable).append("additionalProperties", additionalProperties).toString();
    }


}