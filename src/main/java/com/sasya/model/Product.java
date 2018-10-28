package com.sasya.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Register
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name="product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name="category_id")
    private BigDecimal categoryId;

    @Column(name="sub_category_id")
    private BigDecimal subCategoryId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "unit")
    private String unit;

    @Column(name = "price")
    private Double price;

    @Column(name = "discount_percent")
    private int discountPercent;

    @Column(name = "gst_percent")
    private int gstPercent;

    @Column(name = "active")
    private String active;

    @Column(name = "subscription_allowed")
    private String subscriptionAllowed;

    @Column(name = "image_url")
    private String image;

    @Column(name = "popularity")
    private String popularity;

    @Column(name = "product_new_date")
    private String productNewDate;

    @Column(name = "product_update_date")
    private String productUpdateDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_date")
    private String updatedDate;

    @OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="category_id",insertable = false,updatable = false)
    private Category category;

    @OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="sub_category_id",insertable = false,updatable = false)
    private SubCategory subCategory;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }
}
