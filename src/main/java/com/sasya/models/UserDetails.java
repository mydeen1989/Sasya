package com.sasya.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "users")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Column(name="register_id", nullable = false)
    @Getter
    @Setter
    private Integer registerId;

    @Column(name="phone",  unique = true, nullable = false)
    @Getter
    @Setter
    private Integer phone;

    @Column(name="user_name", unique = true, nullable = false)
    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    @Getter
    @Setter
    private String userName;

    @Column(name="password")
    @Getter
    @Setter
    private String password;

    @Column(name="email",  unique = true, nullable = false)
    @Getter
    @Setter
    private String email;

    @Column(name="family_members_count")
    @Getter
    @Setter
    private String familyMembersCount;

    @Column(name="active")
    @Getter
    @Setter
    private Boolean active;

    @Column(name="device_id")
    @Getter
    @Setter
    private String deviceId;

    @Column(name="deice_type")
    @Getter
    @Setter
    private String deviceType;

    @Column(name="created_by")
    @Getter
    @Setter
    private String createdBy;

    @Column(name="created_time")
    @Getter
    @Setter
    private Date createdTime;

    @Column(name="updated_by")
    @Getter
    @Setter
    private String updatedBy;

    @Column(name="updated_time")
    @Getter
    @Setter
    private Date updatedTime;
}
