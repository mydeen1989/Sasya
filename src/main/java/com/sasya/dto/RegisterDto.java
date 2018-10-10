package com.sasya.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotNull;

@JsonPropertyOrder({"mobile","activation_code"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterDto implements IResponseDto {

    @JsonProperty("mobile")
    @NotNull
    private String mobile ;

    @JsonProperty("activation_code")
    private String activationCode ;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}
