package com.sasya.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@JsonPropertyOrder({"id","name","image_url","active"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName("Subcategory")
public class SubCategoryDto implements IResponseDto {

    @ApiModelProperty(value = "id of Sub Category",required = true)
    @JsonProperty("id")
    private BigDecimal id;

    @ApiModelProperty(required = true)
    @JsonProperty("subCategoryName")
    private String subCategoryName;

    @ApiModelProperty(value = "id of Category",required = true)
    @JsonProperty("categoryId")
    private BigDecimal categoryId;

    @ApiModelProperty(required = true)
    @JsonProperty("categoryName")
    private String categoryName;

    @ApiModelProperty
    @JsonProperty("image_url")
    private String image_url;

    @ApiModelProperty(required = false)
    @JsonProperty("active")
    private String active;

    @ApiModelProperty(required = false,value = "parent category information")
    @JsonProperty("category")
    private CategoryDto parentCategory;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
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

    public BigDecimal getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(BigDecimal categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryDto getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryDto parentCategory) {
        this.parentCategory = parentCategory;
    }
}
