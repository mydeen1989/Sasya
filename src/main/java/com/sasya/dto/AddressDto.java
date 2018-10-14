package com.sasya.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.validation.constraints.*;

@JsonPropertyOrder({"user_id","address","city","state","country","pincode","landmark","address_type","secondary_mobile"})
public class AddressDto {

    @NotNull
    @Size(min=1)
    @ApiModelProperty(value = "User Id of an existing user. Usually id is hidden to the user view",required = true)
    @JsonProperty("user_id")
    private String userId;

    @ApiModelProperty(required = true)
    @Size(min=1,max=500)
    @JsonProperty("address")
    private String address;

    @ApiModelProperty(required = true)
    @Size(min=1,max=50)
    @JsonProperty("city")
    private String city;

    @ApiModelProperty(required = true)
    @Size(min=5,max=50)
    @JsonProperty("state")
    private String state;

    @ApiModelProperty(required = true,allowableValues = "INDIA")
    @Pattern(regexp = "INDIA",flags = Pattern.Flag.CASE_INSENSITIVE)
    @JsonProperty("country")
    private String country;

    @ApiModelProperty(required = true)
    @Size(min=6,max = 6)
    @JsonProperty("pincode")
    private String pincode;

    @JsonProperty("landmark")
    private String landmark;

    @ApiModelProperty(required = true,allowableValues = "HOME/OFFICE")
    @Pattern(regexp = "HOME|OFFICE",flags = Pattern.Flag.CASE_INSENSITIVE)
    @JsonProperty("address_type")
    private String addressType;

    @JsonProperty("secondary_mobile")
    private String secondary_mobile;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getSecondary_mobile() {
        return secondary_mobile;
    }

    public void setSecondary_mobile(String secondary_mobile) {
        this.secondary_mobile = secondary_mobile;
    }
}
