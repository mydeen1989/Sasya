package com.sasya.repository;

import com.sasya.model.Category;
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

    public boolean deleteCategory(BigDecimal id);

    public Category findCategory(BigDecimal id);

    public <T> void mergeObject(T object);

}
