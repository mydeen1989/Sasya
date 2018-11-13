package com.sasya.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sasya.constant.SasyaConstants;
import com.sasya.dto.CategoryDto;
import com.sasya.dto.ProductDto;
import com.sasya.dto.SubCategoryDto;
import com.sasya.dto.UserDto;
import com.sasya.exception.SasyaException;
import com.sasya.model.*;
import com.sasya.repository.AdminDAO;
import com.sasya.repository.CommonDAO;
import com.sasya.response.SasyaResponse;
import com.sasya.util.Mapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.*;

@Service
public class AdminServiceImpl {

    @Inject
    private AdminDAO adminDAO;

    @Inject
    private CommonDAO commonDAO;

    @Inject
    private CommonServiceImpl commonService;

    Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);


    public ResponseEntity addCategory(MultipartFile file, String categoryName) {

        try {
            List<Category> categoriesList = commonDAO.getAllCategory(Collections.singletonList(categoryName));
            if(categoriesList!=null && categoriesList.size()>0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.CATEGORY_ALREADY_EXIST));
            }
            String url = getS3ImageUrl(file);
            Category categoryEntity = new Category();
            categoryEntity.setName(categoryName);
            categoryEntity.setImage(url);
            categoryEntity.setActive("1");
            categoryEntity.setCreatedBy(SasyaConstants.SYSTEM_USER);
            categoryEntity.setCreatedDate(SasyaConstants.formatter.format(new Date()));
            categoryEntity = adminDAO.saveCategory(categoryEntity);
            CategoryDto category = Mapper.convertCategoryEntityToDTO(categoryEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(SasyaResponse.build(
                    SasyaConstants.SUCCESS, SasyaConstants.CATEGORY_ADDED_SUCCESSFULLY,category));

        } catch (Exception exp) {
            logger.error("ADD CATEGORY FAILED",exp);
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity deleteCategory(BigDecimal id) {
        try {
            Category category = commonDAO.getCategory(id);
            if (Objects.nonNull(category)) {
                adminDAO.deleteCategory(category);
                return ResponseEntity.ok().body(SasyaResponse.build(SasyaConstants.SUCCESS, SasyaConstants.CATEGORY_REMOVED_SUCCESSFULLY));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.CATEGORY_NOT_FOUND));
        } catch (Exception exp) {
            logger.error("DELETE CATEGORY FAILED",exp);
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @param file
     * @return
     */
    public ResponseEntity updateCategory(BigDecimal id, MultipartFile file, String categoryName) {
        try {
            Category category = commonDAO.getCategory(id);
            if (category == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.CATEGORY_NOT_FOUND));
            }
            String url = getS3ImageUrl(file);
            category.setImage(url);
            if (categoryName != null) {
                category.setName(categoryName);
            }
            category.setUpdatedBy(id.toPlainString());
            category.setUpdatedDate(SasyaConstants.formatter.format(new Date()));
            adminDAO.mergeObject(category);
            return ResponseEntity.status(HttpStatus.OK).body(SasyaResponse.build(SasyaConstants.SUCCESS, SasyaConstants.CATEGORY_UPDATED_SUCCESSFULLY));
        } catch (SasyaException sasyaExp) {
            throw sasyaExp;
        } catch (Exception exp) {
            logger.error("UPDATE CATEGORY FAILED",exp);
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity addSubCategory(MultipartFile file, String subCategoryName, BigDecimal categoryId) {

        try {
            List<SubCategory> isSubExist = commonDAO.getAllSubCategory(Collections.singletonList(subCategoryName));
            if (isSubExist != null && isSubExist.size() > 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.SUB_CATEGORY_ALREADY_EXIST));
            }
            Category parent =  commonDAO.getCategory(categoryId);
            if(parent==null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.CATEGORY_NOT_FOUND));
            }
//            String url = getS3ImageUrl(file);
            SubCategory subCategoryEntity = new SubCategory();
            subCategoryEntity.setName(subCategoryName);
//            subCategoryEntity.setImage(url);
            subCategoryEntity.setActive("1");
            subCategoryEntity.setCategoryId(categoryId);
            subCategoryEntity.setCreatedBy(SasyaConstants.SYSTEM_USER);
            subCategoryEntity.setCreatedDate(SasyaConstants.formatter.format(new Date()));
            subCategoryEntity = adminDAO.saveSubCategory(subCategoryEntity);
            subCategoryEntity.setCategory(parent);
            SubCategoryDto subCategory = Mapper.convertSubCategoryEntitytoDTO(subCategoryEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(SasyaResponse.build(SasyaConstants.SUCCESS,
                    SasyaConstants.SUB_CATEGORY_ADDED_SUCCESSFULLY, subCategory));
        } catch (Exception exp) {
            logger.error("ADD SUB CATEGORY FAILED",exp);
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity deleteSubCategory(BigDecimal id) {
        try {
            SubCategory subCategory = commonDAO.getSubCategory(id);
            if (Objects.nonNull(subCategory)) {
                adminDAO.deleteSubCategory(subCategory);
                return ResponseEntity.ok().body(SasyaResponse.build(SasyaConstants.SUCCESS, SasyaConstants.SUB_CATEGORY_REMOVED_SUCCESSFULLY));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.SUB_CATEGORY_NOT_FOUND));
        } catch (Exception exp) {
            logger.error("DELETE SUB CATEGORY FAILED",exp);
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @param file
     * @return
     */
    public ResponseEntity updateSubCategory(BigDecimal id, MultipartFile file, String subCategoryName) {
        try {
            SubCategory subCategory = commonDAO.getSubCategory(id);
            if (subCategory == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.SUB_CATEGORY_NOT_FOUND));
            }
            String url = getS3ImageUrl(file);
            subCategory.setImage(url);
            if (subCategoryName != null) {
                subCategory.setName(subCategoryName);
            }
            subCategory.setUpdatedBy(id.toPlainString());
            subCategory.setUpdatedDate(SasyaConstants.formatter.format(new Date()));
            adminDAO.mergeObject(subCategory);
            return ResponseEntity.status(HttpStatus.OK).body(SasyaResponse.build(SasyaConstants.SUCCESS, SasyaConstants.SUB_CATEGORY_UPDATED_SUCCESSFULLY));
        } catch (SasyaException sasyaExp) {
            throw sasyaExp;
        } catch (Exception exp) {
            logger.error("UPDATE SUB CATEGORY FAILED",exp);
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity addProduct(ProductDto productDto) {
        try {
            Product productEntity = adminDAO.saveProduct(Mapper.convertDTOObjectToEntity(productDto));
            ProductDto product = Mapper.convertEntityToDTOObject(productEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(SasyaResponse.build(SasyaConstants.SUCCESS,
                    SasyaConstants.PRODUCT_ADDED_SUCCESSFULLY, product));

        } catch (Exception exp) {
            logger.error("ADD PRODUCT FAILED",exp);
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity deleteProduct(BigDecimal id) {
        try {
            Product product = commonDAO.getProduct(id);
            if (Objects.nonNull(product)) {
                adminDAO.deleteProduct(product);
                return ResponseEntity.status(HttpStatus.OK).body(SasyaResponse.build(SasyaConstants.SUCCESS, SasyaConstants.PRODUCT_REMOVED_SUCCESSFULLY));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.PRODUCT_NOT_FOUND));
        } catch (Exception exp) {
            logger.error("DELETE PRODUCT FAILED",exp);
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @param productDto
     * @return
     */
    public ResponseEntity updateProduct(BigDecimal productId, ProductDto productDto) {
        try {
            Product product = commonDAO.getProduct(productId);
            if (product == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.PRODUCT_NOT_FOUND));
            }
            product = Mapper.convertDTOObjectToEntity(productDto);
            product.setUpdatedBy(SasyaConstants.SYSTEM_USER);
            product.setUpdatedDate(SasyaConstants.formatter.format(new Date()));
            adminDAO.mergeObject(product);
            return ResponseEntity.status(HttpStatus.OK).body(SasyaResponse.build(SasyaConstants.SUCCESS, SasyaConstants.PRODUCT_UPDATED_SUCCESSFULLY));
        } catch (SasyaException sasyaExp) {
            throw sasyaExp;
        } catch (Exception exp) {
            logger.error("UPDATE PRODUCT FAILED",exp);
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity addProductImage(MultipartFile file, BigDecimal id) {

        try {
            Product product = commonDAO.getProduct(id);
            if (Objects.nonNull(product)) {
                adminDAO.addProductImage(getS3ImageUrl(file), id);
                return ResponseEntity.status(HttpStatus.OK).body(SasyaResponse.build(SasyaConstants.SUCCESS, SasyaConstants.PRODUCT_IMAGE_ADDED_SUCCESSFULLY));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.PRODUCT_NOT_FOUND));
        } catch (Exception exp) {
            logger.error("ADD PRODUCT IMAGE FAILED",exp);
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @param ids
     * @return
     */
    public ResponseEntity getAllUsers(List<BigDecimal> ids) {
        try {
            String userId = null;
            if (CollectionUtils.isNotEmpty(ids)) {
                StringBuilder builder = new StringBuilder(StringUtils.EMPTY);
                ids.forEach(id -> {
                    builder.append(id).append(",");
                });
                userId = builder.deleteCharAt(builder.lastIndexOf(",")).toString();
            }
            List<User> users = adminDAO.getAllUsers(userId);
            if (CollectionUtils.isEmpty(users)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.USER_NOT_FOUND));
            }
            List<UserDto> userDtos = new ArrayList<>();
            users.forEach(user -> {
                UserDto userDto = Mapper.convertUserModelToUserDto(user);
                userDtos.add(userDto);
            });
            return ResponseEntity.status(HttpStatus.OK).body(userDtos);
        } catch (SasyaException sasyaExp) {
            throw sasyaExp;
        } catch (Exception exp) {
            logger.error("GET ALL USERS FAILED",exp);
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private String getS3ImageUrl(MultipartFile file) throws IOException {

        AWSKeyGen awsKeyGen = adminDAO.getAWSKeyGen();
        AWSCredentials credentials = new BasicAWSCredentials(awsKeyGen.getAwsAccessKey(), awsKeyGen.getAwsSecretKey());
        // create a client connection based on credentials
        AmazonS3Client s3client = new AmazonS3Client(credentials);
        // upload file to folder and set it to public
        String fileName = awsKeyGen.getFolderName() + SasyaConstants.SUFFIX + file.getOriginalFilename();
        s3client.putObject(new PutObjectRequest(awsKeyGen.getBucketName(), fileName, convertMultiPartToFile(file))
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return s3client.getResourceUrl(awsKeyGen.getBucketName(), fileName);
    }

    private static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
