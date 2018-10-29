package com.sasya.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * UserController
 */
@JsonPropertyOrder({"mobile","otp","password"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDto implements IResponseDto{

    @ApiModelProperty(required = true)
    @Size(min=10,max=10,message = "Invalid mobile number")
    @JsonProperty("mobile")
    private String mobile;

    @ApiModelProperty(required = true)
    @NotNull(message = "OTP not available")
    @NotBlank(message = "OTP not available")
    @JsonProperty("otp")
    private String otp;

    @ApiModelProperty
    @JsonProperty("password")
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
