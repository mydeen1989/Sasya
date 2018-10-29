package com.sasya.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Register
 */
@Entity
@Table(name = "register")
public class Register {


    @Id
    @Column(name = "register_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "phone", unique = true, nullable = false)
    private BigDecimal phone;

    @Column(name = "activation_code")
    private String activationCode;

    @Column(name = "created_by")
    private String createdBy;

    @OneToOne(mappedBy="register",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private User user;

    @Column(name = "created_date")
    private String createdDate;


    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getPhone() {
        return phone;
    }

    public void setPhone(BigDecimal phone) {
        this.phone = phone;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
