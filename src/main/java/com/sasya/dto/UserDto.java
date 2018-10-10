package com.sasya.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({"username","password","email","family_members_count","device_id","device_type","register"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements IResponseDto{

    @JsonProperty("username")
    private String userName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @JsonProperty("family_members_count")
    private BigDecimal familyMembersCount;

    @JsonProperty("device_id")
    private String deviceId;

    @JsonProperty("device_type")
    private String deviceType;

    @JsonProperty("register")
    private RegisterDto register;

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
}
