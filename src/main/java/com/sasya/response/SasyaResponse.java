package com.sasya.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sasya.dto.IResponseDto;

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

    @JsonProperty("result")
    private IResponseDto responseDto;

    @JsonProperty("resultArray")
    private List<? extends IResponseDto> responseDtoArray;


    public SasyaResponse() {

    }

    public SasyaResponse(String status,String message) {
        this.status = status;
        this.message = message;
    }

    public SasyaResponse(String status,String message,IResponseDto responseDto) {
        this.status = status;
        this.message = message;
        this.responseDto = responseDto;
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

    public IResponseDto getResponseDto() {
        return responseDto;
    }

    public void setResponseDto(IResponseDto responseDto) {
        this.responseDto = responseDto;
    }


    public List<? extends IResponseDto> getResponseDtoArray() {
        return responseDtoArray;
    }

    public void setResponseDtoArray(List<? extends IResponseDto> responseDtoArray) {
        this.responseDtoArray = responseDtoArray;
    }

    public static SasyaResponse build(String status, String message){
        SasyaResponse response = new SasyaResponse();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }

    public static SasyaResponse build(String status,String message,IResponseDto responseDto){
        SasyaResponse response = new SasyaResponse();
        if(responseDto!=null){
            response.setResponseDto(responseDto);
        }
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }

    public static SasyaResponse build(String status, String message, List<? extends IResponseDto> resultArray){
        SasyaResponse response = new SasyaResponse();
        if(resultArray!=null){
            response.setResponseDtoArray(resultArray);
        }
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }
}
