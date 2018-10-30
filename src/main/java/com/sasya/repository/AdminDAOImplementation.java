package com.sasya.repository;

import com.sasya.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

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
    public Category saveCategory(Category category){
        entityManager.persist(category);
        return category;
    }


    /**
     * @param subCategory
     */
    @Override
    public SubCategory saveSubCategory(SubCategory subCategory){
        entityManager.persist(subCategory);
        entityManager.flush();
        return subCategory;
    }


    @Override
    public void deleteCategory(Category category) {
         entityManager.remove(category);
    }


    @Override
    public void deleteSubCategory(SubCategory subCategory) {
        entityManager.remove(subCategory);
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
    public Product saveProduct(Product product){
        entityManager.persist(product);
        entityManager.flush();
        return product;
    }

    @Override
    public void deleteProduct(Product product) {
        entityManager.remove(product);
    }

    @Override
    public <T> void mergeObject(T object) {
        entityManager.merge(object);
    }


    @Override
    public List<User> getAllUsers(String userIds) {
        StringBuilder query = new StringBuilder("from User u where u.active='1' and");
        if (StringUtils.isEmpty(userIds)) {
            return entityManager.createQuery("from User u where u.active='1'")
                    .getResultList();
        } else {
            query.append(" u.id in (" + userIds + ")");
            return  entityManager.createQuery(query.toString())
                    .getResultList();
        }
    }

    public AWSKeyGen getAWSKeyGen(){
       List<AWSKeyGen> awsKeyGen=  entityManager.createQuery("from AWSKeyGen")
                .getResultList();

        if(awsKeyGen.isEmpty()){
            return null;
        }
        return awsKeyGen.get(0);
    }
}
