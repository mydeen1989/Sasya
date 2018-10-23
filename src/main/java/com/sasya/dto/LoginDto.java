package com.sasya.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * UserController
 */
@JsonPropertyOrder({"mobile","password","otp"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDto implements IResponseDto{

    @ApiModelProperty(required = true)
    @NotNull
    @Size(min=10,max=10)
    @JsonProperty("mobile")
    private String mobile;

    @ApiModelProperty(required = true)
    @NotNull
    @JsonProperty("otp")
    private String otp;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
