package com.sasya.dto;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * UserDto
 */
@JsonPropertyOrder({"id","username","password","email","family_members_count","device_id","device_type","otp","register"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("user")
public class UserDto implements IResponseDto{

    @ApiModelProperty(value = "User Id")
    @JsonProperty(value = "id")
    private BigDecimal id;

    @Size(min=3,max = 100,message="Invalid user name")
    @ApiModelProperty(required = true)
    @JsonProperty("username")
    private String userName;


    @ApiModelProperty
    @JsonProperty("password")
    private String password;


    @Email(message="invalid email information")
    @ApiModelProperty(required = true)
    @JsonProperty("email")
    private String email;

    @JsonProperty("family_members_count")
    private BigDecimal familyMembersCount;

    @NotNull(message = "device id not available")
    @NotBlank(message="device id not available")
    @ApiModelProperty(required = true)
    @JsonProperty("device_id")
    private String deviceId;

    @NotNull(message="device type not available")
    @NotBlank(message="device type not available")
    @ApiModelProperty(required = true)
    @JsonProperty("device_type")
    private String deviceType;

    @NotNull(message = "OTP value not available")
    @NotBlank(message="OTP value not available")
    @ApiModelProperty(required = true)
    @JsonProperty("otp")
    private String otp;

    @NotNull(message = "register mobile information not available")
    @ApiModelProperty(required = true)
    @JsonProperty("register")
    private RegisterDto register;

    @ApiModelProperty(required = false)
    @JsonProperty("address")
    private List<AddressDto> address;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

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

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public List<AddressDto> getAddress() {
        return address;
    }

    public void setAddress(List<AddressDto> address) {
        this.address = address;
    }
}
