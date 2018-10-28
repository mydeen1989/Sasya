package com.sasya.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@JsonPropertyOrder({"productId","categoryId","subCategoryId","productName","categoryName","subCategoryName","image_url",
        "active","subscriptionAllowed","popularity","description","weight","unit","price","discountPercent","gstPercent",
        "netprice","productNewDate","productUpdateDate","category","subCategory"})
public class ProductDto implements IResponseDto {

    @ApiModelProperty(value = "id of Product",required = true)
    @JsonProperty("productId")
    private BigDecimal productId;

    @ApiModelProperty(value = "id of Category",required = true)
    @JsonProperty("categoryId")
    private BigDecimal categoryId;

    @ApiModelProperty(value = "id of Sub Category")
    @JsonProperty("subCategoryId")
    private BigDecimal subCategoryId;

    @ApiModelProperty(required = true)
    @JsonProperty("productName")
    private String productName;

    @ApiModelProperty
    @JsonProperty("categoryName")
    private String categoryName;

    @ApiModelProperty
    @JsonProperty("subCategoryName")
    private String subCategoryName;

    @ApiModelProperty
    @JsonProperty("image_url")
    private String image_url;

    @ApiModelProperty
    @JsonProperty("active")
    private String active;

    @ApiModelProperty
    @JsonProperty("subscriptionAllowed")
    private String subscriptionAllowed;

    @ApiModelProperty
    @JsonProperty("popularity")
    private String popularity;

    @ApiModelProperty
    @JsonProperty("description")
    private String description;

    @ApiModelProperty
    @JsonProperty("weight")
    private BigDecimal weight;

    @ApiModelProperty
    @JsonProperty("unit")
    private String unit;

    @ApiModelProperty
    @JsonProperty("price")
    private Double price;

    @ApiModelProperty
    @JsonProperty("discountPercent")
    private int discountPercent;

    @ApiModelProperty
    @JsonProperty("gstPercent")
    private int gstPercent;

    @ApiModelProperty
    @JsonProperty("netprice")
    private Double netprice;

    @ApiModelProperty
    @JsonProperty("productNewDate")
    private String productNewDate;

    @ApiModelProperty
    @JsonProperty("productUpdateDate")
    private String productUpdateDate;

    public BigDecimal getProductId() {
        return productId;
    }

    public void setProductId(BigDecimal productId) {
        this.productId = productId;
    }

    public BigDecimal getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(BigDecimal categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(BigDecimal subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getSubscriptionAllowed() {
        return subscriptionAllowed;
    }

    public void setSubscriptionAllowed(String subscriptionAllowed) {
        this.subscriptionAllowed = subscriptionAllowed;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getGstPercent() {
        return gstPercent;
    }

    public void setGstPercent(int gstPercent) {
        this.gstPercent = gstPercent;
    }

    public Double getNetprice() {
        return netprice;
    }

    public void setNetprice(Double netprice) {
        this.netprice = netprice;
    }

    public String getProductNewDate() {
        return productNewDate;
    }

    public void setProductNewDate(String productNewDate) {
        this.productNewDate = productNewDate;
    }

    public String getProductUpdateDate() {
        return productUpdateDate;
    }

    public void setProductUpdateDate(String productUpdateDate) {
        this.productUpdateDate = productUpdateDate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
