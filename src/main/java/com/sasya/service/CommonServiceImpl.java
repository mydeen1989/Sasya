package com.sasya.service;

import com.sasya.constant.SasyaConstants;
import com.sasya.dto.CategoryDto;
import com.sasya.dto.ProductDto;
import com.sasya.dto.SubCategoryDto;
import com.sasya.exception.SasyaException;
import com.sasya.model.Category;
import com.sasya.model.Product;
import com.sasya.model.SubCategory;
import com.sasya.repository.CommonDAO;
import com.sasya.response.SasyaResponse;
import com.sasya.util.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


/**
 * CommonServiceImpl
 */
@Service
public class CommonServiceImpl {


    @Inject
    private CommonDAO commonDAO;


    /**
     * @return
     */
    public ResponseEntity getAllCategory() {
        try {
            List<Category> categories = commonDAO.getAllCategory();
            if (CollectionUtils.isEmpty(categories)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.CATEGORY_NOT_FOUND));
            }
            List<CategoryDto> categoryDtos = new ArrayList<>();
            categories.forEach(category -> {
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setId(category.getId());
                categoryDto.setName(category.getName());
                categoryDto.setImage_url(category.getImage());
                categoryDto.setActive(category.getActive());
                categoryDtos.add(categoryDto);
            });
            return ResponseEntity.status(HttpStatus.OK).body(categoryDtos);
        } catch (SasyaException sasyaExp) {
            throw sasyaExp;
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @return
     */
    public ResponseEntity getAllSubCategory() {
        try {
            List<SubCategory> subCategories = commonDAO.getAllSubCategory();
            if (CollectionUtils.isEmpty(subCategories)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.SUB_CATEGORY_NOT_FOUND));
            }
            List<SubCategoryDto> subCategoryDtos = new ArrayList<>();
            subCategories.forEach(subCategory -> {
                SubCategoryDto subCategoryDto = new SubCategoryDto();
                subCategoryDto.setId(Objects.nonNull(subCategory.getId()) ? subCategory.getId() : null);
                subCategoryDto.setSubCategoryName(Objects.nonNull(subCategory.getName()) ? subCategory.getName() : null);
                if (Objects.nonNull(subCategory.getCategory())) {
                    subCategoryDto.setCategoryId(Objects.nonNull(subCategory.getCategory().getId()) ? subCategory.getCategory().getId() : null);
                    subCategoryDto.setCategoryName(Objects.nonNull(subCategory.getCategory().getName()) ? subCategory.getCategory().getName() : null);
                }
                subCategoryDto.setImage_url(Objects.nonNull(subCategory.getImage()) ? subCategory.getImage() : null);
                subCategoryDto.setActive(Objects.nonNull(subCategory.getActive()) ? subCategory.getActive() : null);
                subCategoryDtos.add(subCategoryDto);
            });
            return ResponseEntity.status(HttpStatus.OK).body(subCategoryDtos);
        } catch (SasyaException sasyaExp) {
            throw sasyaExp;
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @param categoryId
     * @param subCategoryId
     * @param popularity
     * @return
     */
    public ResponseEntity getAllProducts(BigDecimal categoryId, BigDecimal subCategoryId, String popularity,String filter,List<BigDecimal> productIds) {
        try {
            List<Product> products = commonDAO.getAllProducts(categoryId, subCategoryId, popularity,filter,productIds);
            if (CollectionUtils.isEmpty(products)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.PRODUCT_NOT_FOUND));
            }
            List<ProductDto> productDtos = new ArrayList<>();
            products.forEach(product -> {
                ProductDto productDto = new ProductDto();
                Mapper.convertEntityToDTOObject(product, productDto);
                productDtos.add(productDto);
            });

            return ResponseEntity.status(HttpStatus.OK).body(productDtos);
        } catch (SasyaException sasyaExp) {
            throw sasyaExp;
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param id
     * @return
     */
    public ResponseEntity getProduct(BigDecimal id) {
        try {
            Product product = commonDAO.getProduct(id);
            if (Objects.isNull(product)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.PRODUCT_NOT_FOUND));
            }
            ProductDto productDto = new ProductDto();
            Mapper.convertEntityToDTOObject(product, productDto);
            return ResponseEntity.status(HttpStatus.OK).body(SasyaResponse.build(SasyaConstants.SUCCESS, SasyaConstants.PRODUCT_FOUND, Collections.singletonList(productDto)));
        } catch (SasyaException sasyaExp) {
            throw sasyaExp;
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
