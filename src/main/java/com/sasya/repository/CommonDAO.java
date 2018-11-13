package com.sasya.repository;

import com.sasya.model.Category;
import com.sasya.model.Product;
import com.sasya.model.SubCategory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * UserDAO
 */
@Repository
@Transactional
public interface CommonDAO {

    /**
     * @return
     */
    public List<Category> getAllCategory(List<String> categories);

    /**
     * @param categoryId
     * @param subCategoryId
     * @param popularity
     * @return
     */
    public List<Product> getAllProducts(BigDecimal categoryId, BigDecimal subCategoryId, String popularity, String productIds);

    /**
     * @param id
     * @return
     */
    public Category getCategory(BigDecimal id);

    /**
     * @param id
     * @return
     */
    public Product getProduct(BigDecimal id);

    /**
     * @param id
     * @return
     */
    public SubCategory getSubCategory(BigDecimal id);

    /**
     * @return
     */
    public List<SubCategory> getAllSubCategory(List<String> subCategoryNames);

}
