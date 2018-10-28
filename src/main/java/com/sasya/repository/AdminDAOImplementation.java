package com.sasya.repository;

import com.sasya.constant.SasyaConstants;
import com.sasya.exception.SasyaException;
import com.sasya.model.Category;
import com.sasya.model.Product;
import com.sasya.model.SubCategory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;

/**
 * UserDAOImplementation
 */
@Transactional
@Component
public class AdminDAOImplementation implements AdminDAO {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * @param category
     */
    @Override
    public void saveCategory(Category category){
        entityManager.persist(category);
    }


    /**
     * @param subCategory
     */
    @Override
    public void saveSubCategory(SubCategory subCategory){
        entityManager.persist(subCategory);
    }


    @Override
    public boolean deleteCategory(BigDecimal id) {
        Query updateQuery = entityManager.createNativeQuery("update Category c set c.active=?1 where c.id=?2");
        updateQuery.setParameter(1,'0');
        updateQuery.setParameter(2, id);
        int result = updateQuery.executeUpdate();
        if(result>0)
            return true;
        return false;
    }


    @Override
    public boolean deleteSubCategory(BigDecimal id) {
        Query updateQuery = entityManager.createNativeQuery("update SubCategory c set c.active=?1 where c.id=?2");
        updateQuery.setParameter(1,'0');
        updateQuery.setParameter(2, id);
        int result = updateQuery.executeUpdate();
        if(result>0)
            return true;
        return false;
    }

    @Override
    public boolean addProductImage(String url, BigDecimal id){
        Query updateQuery = entityManager.createNativeQuery("update Product p set p.image_url=?1 where p.product_id=?2");
        updateQuery.setParameter(1,url);
        updateQuery.setParameter(2, id);
        int result = updateQuery.executeUpdate();
        if(result>0)
            return true;
        return false;
    }
    /**
     * @param product
     */
    @Override
    public void saveProduct(Product product){
        entityManager.persist(product);
    }

    @Override
    public boolean deleteProduct(BigDecimal id) {
        Query updateQuery = entityManager.createNativeQuery("update Product p set p.active=?1 where p.product_id=?2");
        updateQuery.setParameter(1,'0');
        updateQuery.setParameter(2, id);
        int result = updateQuery.executeUpdate();
        if(result>0)
            return true;
        return false;
    }

    @Override
    public <T> void mergeObject(T object) {
        entityManager.merge(object);
    }
}
