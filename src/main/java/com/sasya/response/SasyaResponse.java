package com.sasya.response;

import com.fasterxml.jackson.annotation.JsonProperty;



public class SasyaResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    public SasyaResponse() {

    }

    public SasyaResponse(String status,String message) {
        this.status = status;
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
