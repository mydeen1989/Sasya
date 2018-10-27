package com.sasya.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sasya.constant.SasyaConstants;
import com.sasya.exception.SasyaException;
import com.sasya.model.Category;
import com.sasya.repository.AdminDAO;
import com.sasya.response.SasyaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Service
public class AdminServiceImpl {

    @Inject
    private AdminDAO adminDAO;

    @Value("${aws_access_key}")
    private String accessKey;

    @Value("${aws_secret_key}")
    private String secretKey;

    @Value("${aws_bucket_name}")
    private String bucketName;

    @Value("${aws_folder_name}")
    private String folderName;


    public ResponseEntity addCategory(MultipartFile file, String categoryName) {

        try {
            String url = getS3ImageUrl(file);
            Category category = new Category();
            category.setName(categoryName);
            category.setImage(url);
            category.setActive("1");
            category.setCreatedBy(SasyaConstants.SYSTEM_USER);
            category.setCreatedDate(SasyaConstants.formatter.format(new Date()));
            adminDAO.saveCategory(category);
            return ResponseEntity.ok().body(SasyaResponse.build(SasyaConstants.SUCCESS, SasyaConstants.CATEGORY_ADDED_SUCCESSFULLY));

        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity deleteCategory(BigDecimal id) {
        try {
            Category category = adminDAO.findCategory(id);
            if (Objects.nonNull(category)) {
                adminDAO.deleteCategory(id);
                return ResponseEntity.ok().body(SasyaResponse.build(SasyaConstants.SUCCESS, SasyaConstants.CATEGORY_REMOVED_SUCCESSFULLY));
            }
            return ResponseEntity.badRequest().body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.CATEGORY_NOT_FOUND));
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @param file
     * @return
     */
    public ResponseEntity updateCategory(BigDecimal id, MultipartFile file, String categoryName) {
        try {
            Category category = adminDAO.findCategory(id);
            if(category==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE,SasyaConstants.CATEGORY_NOT_FOUND));
            }
            String url = getS3ImageUrl(file);
            category.setImage(url);
            if(categoryName !=null) {
                category.setName(categoryName);
            }
            category.setUpdatedBy(id.toPlainString());
            category.setUpdatedDate(SasyaConstants.formatter.format(new Date()));
            adminDAO.mergeObject(category);
            return ResponseEntity.ok().body(SasyaResponse.build(SasyaConstants.SUCCESS,SasyaConstants.CATEGORY_UPDATED_SUCCESSFULLY));
        } catch (SasyaException sasyaExp){
            throw sasyaExp;
        }
        catch (Exception exp) {
            throw new SasyaException(exp.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private String getS3ImageUrl(MultipartFile file) throws IOException {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        // create a client connection based on credentials
        AmazonS3Client s3client = new AmazonS3Client(credentials);
        // upload file to folder and set it to public
        String fileName = folderName + SasyaConstants.SUFFIX + file.getOriginalFilename();
        s3client.putObject(new PutObjectRequest(bucketName, fileName, convertMultiPartToFile(file))
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return s3client.getResourceUrl(bucketName,fileName);
    }

    private static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
