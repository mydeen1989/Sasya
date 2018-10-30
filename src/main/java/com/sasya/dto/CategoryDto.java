package com.sasya.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@JsonPropertyOrder({"id","name","image_url","active"})
@JsonTypeName("category")
public class CategoryDto implements IResponseDto {

    @ApiModelProperty(value = "id of Category",required = true)
    @JsonProperty("id")
    private BigDecimal id;

    @ApiModelProperty(required = true)
    @JsonProperty("name")
    private String name;

    @ApiModelProperty
    @JsonProperty("image_url")
    private String image_url;

    @ApiModelProperty(required = false)
    @JsonProperty("active")
    private String active;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
