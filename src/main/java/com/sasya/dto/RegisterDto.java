package com.sasya.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotNull;

/**
 * RegisterDto
 */
@JsonPropertyOrder({"mobile"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterDto implements IResponseDto {

    @JsonProperty("mobile")
    @NotNull
    private String mobile ;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
