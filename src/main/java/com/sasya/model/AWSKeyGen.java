package com.sasya.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "aws_keys")
public class AWSKeyGen {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "aws_access_key")
    private String awsAccessKey;

    @Column(name = "aws_secret_key")
    private String awsSecretKey;

    @Column(name = "aws_bucket_name")
    private String bucketName;

    @Column(name = "aws_folder_name")
    private String folderName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAwsAccessKey() {
        return awsAccessKey;
    }

    public void setAwsAccessKey(String awsAccessKey) {
        this.awsAccessKey = awsAccessKey;
    }

    public String getAwsSecretKey() {
        return awsSecretKey;
    }

    public void setAwsSecretKey(String awsSecretKey) {
        this.awsSecretKey = awsSecretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}
