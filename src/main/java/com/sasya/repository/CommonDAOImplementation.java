package com.sasya.repository;

import com.sasya.model.Category;
import com.sasya.model.Product;
import com.sasya.model.SubCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDAOImplementation
 */
@Transactional
@Component
public class CommonDAOImplementation implements CommonDAO {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * @return
     */
    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = entityManager.createQuery("select c from Category c where c.active='1'")
                .getResultList();
        if (categories.isEmpty()) {
            return null;
        }
        return categories;
    }

    /**
     * @return
     */
    @Override
    public List<SubCategory> getAllSubCategory() {
        List<SubCategory> subCategories = entityManager.createQuery("select s from SubCategory s where s.active='1'")
                .getResultList();
        if (subCategories.isEmpty()) {
            return null;
        }
        return subCategories;
    }

    /**
     * @param categoryId
     * @param subCategoryId
     * @param popularity
     * @return
     */
    @Override
    public List<Product> getAllProducts(BigDecimal categoryId, BigDecimal subCategoryId, String popularity, String productIds) {

        List<Product> products = new ArrayList<>();
        if(categoryId==null && subCategoryId==null && StringUtils.isBlank(popularity) &&
                StringUtils.isEmpty(productIds)){
            return entityManager.createQuery("from Product p Where p.active='1'").getResultList();
        }
        StringBuilder query = new StringBuilder("from Product p Where p.active='1' and ");
        if(categoryId!=null && !categoryId.equals(BigDecimal.ZERO)) {
            query.append( " p.category.id="+categoryId+" and ");
        }
        if(subCategoryId!=null && !subCategoryId.equals(BigDecimal.ZERO)){
            query.append(" p.subCategory.id="+subCategoryId+" and ");
        }
        if(StringUtils.isNotBlank(popularity)) {
            query.append(" p.popularity="+popularity+" and ");
        }
        if(StringUtils.isNotEmpty(productIds)) {
            query.append(" p.id in ("+productIds+") and");
        }
        query = new StringBuilder(query.substring(0, query.lastIndexOf("and")));
        products = entityManager.createQuery(query.toString())
                .getResultList();
        return products;
    }


    /**
     * @param id
     * @return
     */
    @Override
    public Category getCategory(BigDecimal id) {
        List<Category> category = entityManager.createQuery("select c from Category c where c.id=?1 and c.active='1'")
                .setParameter(1, id)
                .getResultList();
        if (category.isEmpty()) {
            return null;
        }
        return category.get(0);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public SubCategory getSubCategory(BigDecimal id) {
        List<SubCategory> subCategories = entityManager.createQuery("select c from SubCategory c where c.id=?1 and c.active='1'")
                .setParameter(1, id)
                .getResultList();
        if (subCategories.isEmpty()) {
            return null;
        }
        return subCategories.get(0);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Product getProduct(BigDecimal id) {
        List<Product> products = entityManager.createQuery("select p from Product p where p.id=?1 and p.active='1'")
                .setParameter(1, id)
                .getResultList();
        if (products.isEmpty()) {
            return null;
        }
        return products.get(0);
    }

}
