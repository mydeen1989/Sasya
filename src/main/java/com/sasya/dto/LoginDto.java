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
@JsonPropertyOrder({"username","password","otp"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDto implements IResponseDto{

    @ApiModelProperty(required = true)
    @NotNull
    @Size(min=5,max=100)
    @JsonProperty("username")
    private String userName;

    @ApiModelProperty(required = false)
    @JsonProperty("password")
    private String password;

    @ApiModelProperty(required = true)
    @NotNull
    @JsonProperty("otp")
    private String otp;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
