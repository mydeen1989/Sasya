package com.sasya.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * RegisterDto
 */
@JsonPropertyOrder({"mobile"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterDto implements IResponseDto {

    @ApiModelProperty(required = true)
    @NotNull
    @Size(min = 10,max = 10)
    @JsonProperty("mobile")
    private String mobile ;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
