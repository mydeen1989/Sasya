package com.sasya.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * UserDto
 */
@JsonPropertyOrder({"username","password","email","family_members_count","device_id","device_type","otp","register"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements IResponseDto{

    @NotNull
    @Size(min=3,max = 100)
    @ApiModelProperty(required = true)
    @JsonProperty("username")
    private String userName;


    @NotNull
    @Email
    @ApiModelProperty(required = true)
    @JsonProperty("email")
    private String email;

    @JsonProperty("family_members_count")
    private BigDecimal familyMembersCount;

    @NotNull
    @ApiModelProperty(required = true)
    @JsonProperty("device_id")
    private String deviceId;

    @NotNull
    @ApiModelProperty(required = true)
    @JsonProperty("device_type")
    private String deviceType;

    @NotNull
    @ApiModelProperty(required = true)
    @JsonProperty("otp")
    private String otp;

    @NotNull
    @ApiModelProperty(required = true)
    @JsonProperty("register")
    private RegisterDto register;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getFamilyMembersCount() {
        return familyMembersCount;
    }

    public void setFamilyMembersCount(BigDecimal familyMembersCount) {
        this.familyMembersCount = familyMembersCount;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public RegisterDto getRegister() {
        return register;
    }

    public void setRegister(RegisterDto register) {
        this.register = register;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
