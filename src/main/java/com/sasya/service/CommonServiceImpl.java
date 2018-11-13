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
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public ResponseEntity getAllCategory(List<String> categoryNames) {
        try {
            List<Category> categories = commonDAO.getAllCategory(categoryNames);
            if (CollectionUtils.isEmpty(categories)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.CATEGORY_NOT_FOUND));
            }
            List<CategoryDto> categoryDtos = new ArrayList<>();
            categories.forEach(category -> {
                categoryDtos.add(Mapper.convertCategoryEntityToDTO(category));
            });
            //return ResponseEntity.status(HttpStatus.OK).body(categoryDtos);
            return ResponseEntity.status(HttpStatus.OK).body(SasyaResponse.build(SasyaConstants.SUCCESS, "", categoryDtos,"Categories"));
        } catch (SasyaException sasyaExp) {
            throw sasyaExp;
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @return
     */
    public ResponseEntity getAllSubCategory(List<String> subCategoryNames) {
        try {
            List<SubCategory> subCategories = commonDAO.getAllSubCategory(subCategoryNames);
            if (CollectionUtils.isEmpty(subCategories)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.SUB_CATEGORY_NOT_FOUND));
            }
            List<SubCategoryDto> subCategoryDtos = new ArrayList<>();
            subCategories.forEach(subCategory -> {
                subCategoryDtos.add(Mapper.convertSubCategoryEntitytoDTO(subCategory));
            });
            return ResponseEntity.status(HttpStatus.OK).body(SasyaResponse.build(SasyaConstants.SUCCESS,"",subCategoryDtos,"subCategories"));
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
    public ResponseEntity getAllProducts(BigDecimal categoryId, BigDecimal subCategoryId, String popularity,List<BigDecimal> productIds) {
        try {
            String productId = null;
            if(CollectionUtils.isNotEmpty(productIds)) { StringBuilder builder = new StringBuilder(StringUtils.EMPTY);
               productIds.forEach(id -> {
                   builder.append(id).append(",");
               });
                productId =  builder.deleteCharAt(builder.lastIndexOf(",")).toString();
           }
            List<Product> products = commonDAO.getAllProducts(categoryId, subCategoryId, popularity,productId);
            if (CollectionUtils.isEmpty(products)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.PRODUCT_NOT_FOUND));
            }
            List<ProductDto> productDtos = new ArrayList<>();
            products.forEach(product -> {
                productDtos.add( Mapper.convertEntityToDTOObject(product));
            });

            return ResponseEntity.status(HttpStatus.OK).body(productDtos);
        } catch (SasyaException sasyaExp) {
            throw sasyaExp;
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
