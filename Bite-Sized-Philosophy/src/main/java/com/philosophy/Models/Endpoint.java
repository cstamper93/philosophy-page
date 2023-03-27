package com.philosophy.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Endpoint {

    @JsonProperty("next")
    private String endpoint;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
