package com.sasya.repository;

import com.sasya.constant.SasyaConstants;
import com.sasya.exception.SasyaException;
import com.sasya.model.Category;
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
    public Category findCategory(BigDecimal id) {
        try {
            return entityManager.find(Category.class, id);
        }
        catch (Exception exp){
            throw new SasyaException(SasyaConstants.CATEGORY_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public <T> void mergeObject(T object) {
        entityManager.merge(object);
    }
}
