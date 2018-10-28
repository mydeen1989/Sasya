package com.sasya.repository;

import com.sasya.model.Category;
import com.sasya.model.Product;
import com.sasya.model.SubCategory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

/**
 * AdminDAO
 */
@Repository
@Transactional
public interface AdminDAO {

    /**
     * @param category
     */
    public void saveCategory(Category category);

    public void saveSubCategory(SubCategory category);

    public boolean deleteCategory(BigDecimal id);

    public boolean deleteSubCategory(BigDecimal id);

    public void saveProduct(Product product);

    public <T> void mergeObject(T object);

    public boolean addProductImage(String url, BigDecimal id);

    public boolean deleteProduct(BigDecimal id);


}
