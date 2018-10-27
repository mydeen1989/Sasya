package com.sasya.repository;

import com.sasya.model.Category;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * UserDAOImplementation
 */
@Transactional
@Component
public class CommonDAOImplementation implements CommonDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List<Category> getAllCategory(){
        List<Category> categories = entityManager.createQuery("select u from Category u where u.active='1'")
                .getResultList();
        if(categories.isEmpty()){
            return null;
        }
        return categories;
    }

}
