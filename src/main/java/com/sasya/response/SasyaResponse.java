package com.sasya.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sasya.dto.AddressDto;
import com.sasya.dto.IResponseDto;
import org.springframework.validation.FieldError;

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
    private IResponseDto response;

    public SasyaResponse() {

    }

    public SasyaResponse(String status,String message) {
        this.status = status;
        this.message = message;
    }

    public IResponseDto getResponse() {
        return response;
    }

    public void setResponse(IResponseDto response) {
        this.response = response;
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

    public static SasyaResponse build(String status, String message){
        SasyaResponse response = new SasyaResponse();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }

    public static SasyaResponse build(String status, String message,IResponseDto body){
        SasyaResponse response = new SasyaResponse();
        response.setStatus(status);
        response.setMessage(message);
        response.setResponse(body);
        return response;
    }

    public static ObjectNode build(String status, String message, Collection<? extends IResponseDto> resultArray){
        ObjectNode rootNode = new ObjectMapper().createObjectNode();
        rootNode.put("status", status);
        rootNode.put("message", message);
        ArrayNode fields = rootNode.putArray("body");
        resultArray.forEach(obj -> {
            fields.addPOJO(obj);
        });
        return rootNode;
    }
}
