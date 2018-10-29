package com.sasya.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@JsonPropertyOrder({"addressId","address","city","state","country","pincode","landmark","address_type","secondary_mobile"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("address")
public class AddressDto implements IResponseDto {

    @ApiModelProperty(value = "id of Address")
    @JsonProperty("addressId")
    private BigDecimal addressId;

    @ApiModelProperty(required = true)
    @NotBlank(message = "address field should not be empty")
    @Size(min=1,max=500,message = "address field should be greater than 3 characters")
    @JsonProperty("address")
    private String address;

    @ApiModelProperty(required = true)
    @NotBlank(message="city should not be empty")
    @JsonProperty("city")
    private String city;

    @ApiModelProperty(required = true)
    @NotBlank(message = "state should not be empty")
    @JsonProperty("state")
    private String state;

    @ApiModelProperty(required = true,allowableValues = "INDIA")
    @Pattern(regexp = "INDIA",flags = Pattern.Flag.CASE_INSENSITIVE)
    @JsonProperty("country")
    private String country;

    @ApiModelProperty(required = true)
    @NotBlank(message="PINCODE should not be empty")
    @Size(min=6,max = 6)
    @JsonProperty("pincode")
    private String pincode;

    @JsonProperty("landmark")
    private String landmark;

    @ApiModelProperty(required = true,allowableValues = "HOME/OFFICE")
    @Pattern(regexp = "HOME|OFFICE",flags = Pattern.Flag.CASE_INSENSITIVE)
    @NotBlank(message="address type should not be empty")
    @JsonProperty("address_type")
    private String addressType;

    @JsonProperty("secondary_mobile")
    private String secondary_mobile;


    public BigDecimal getAddressId() {
        return addressId;
    }

    public void setAddressId(BigDecimal addressId) {
        this.addressId = addressId;
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
