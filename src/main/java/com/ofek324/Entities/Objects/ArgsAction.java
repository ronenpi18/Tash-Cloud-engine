package com.ofek324.Entities.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "order",
        "uuid"
})
public class ArgsAction {
    @JsonProperty("uuid")
    protected String uuid;
    @JsonProperty("order")
    protected int order;

    public ArgsAction(String uuid, int order) {
        this.uuid = uuid;
        this.order = order;
    }

    public ArgsAction(){}
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
