package com.sasya.repository;

import com.sasya.model.Category;
import com.sasya.model.Product;
import com.sasya.model.SubCategory;
import com.sasya.model.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * AdminDAO
 */
@Repository
@Transactional
public interface AdminDAO {

    /**
     * @param category
     */
    public Category saveCategory(Category category);

    public SubCategory saveSubCategory(SubCategory category);

    public void deleteCategory(Category category);

    public void deleteSubCategory(SubCategory subCategory);

    public Product saveProduct(Product product);

    public <T> void mergeObject(T object);

    public boolean addProductImage(String url, BigDecimal id);

    public void deleteProduct(Product product);

    public List<User> getAllUsers(String userIds);


}
