package com.sasya.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sasya.dto.AddressDto;
import com.sasya.dto.IResponseDto;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * SasyaResponse
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SasyaResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("body")
    @JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT ,use = JsonTypeInfo.Id.NAME,property="type")
    private Collection<? extends IResponseDto> responseArray;


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

    public Collection<? extends IResponseDto> getResponseArray() {
        return responseArray;
    }

    public void setResponseArray(Collection<? extends IResponseDto> responseArray) {
        this.responseArray = responseArray;
    }

    public static SasyaResponse build(String status, String message){
        SasyaResponse response = new SasyaResponse();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }


    public static SasyaResponse build(String status, String message, Collection<? extends IResponseDto> resultArray){
        SasyaResponse response = new SasyaResponse();
        if(resultArray!=null){
            response.setResponseArray(resultArray);
        }
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }
}
