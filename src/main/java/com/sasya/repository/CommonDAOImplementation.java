package com.sasya.repository;

import com.sasya.model.Category;
import com.sasya.model.Product;
import com.sasya.model.SubCategory;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.sasya.util.SasyaUtils.getEmptyStreamOnNull;

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
    public List<Category> getAllCategory(List<String> categories) {
//        StringBuilder query = new StringBuilder("select c from Category c where c.active='1'");
//        if(categories!=null && categories.size()>0){
//            StringBuilder conditionOnName = new StringBuilder(" and c.name in ('");
//            categories.forEach(categoryName -> {
//                        conditionOnName.append(categoryName).append("','");
//                    }
//            );
//            conditionOnName.replace(conditionOnName.lastIndexOf(","), conditionOnName.length(), ")");
//            query.append(conditionOnName);
//        }
//        List<Category> categoriesList = entityManager.createQuery(query.toString())
//                .getResultList();
//        if (categoriesList.isEmpty()) {
//            return null;
//        }
//        return categoriesList;

        Criteria criteria= entityManager.unwrap(Session.class).createCriteria(Category.class);
        if(categories!=null && categories.size()>0){
            Criterion categoryNameSelect = Restrictions.in("name", categories);
            criteria.add(categoryNameSelect);
        }
        List<Category> categoriesList = criteria.list();
        if(categoriesList.isEmpty()) {
            return null;
        }
        return categoriesList;
    }

    /**
     * @return
     */
    @Override
    public List<SubCategory> getAllSubCategory(List<String> subCategoryNames) {
//        List<SubCategory> subCategories = entityManager.createQuery("select s from SubCategory s where s.active='1'")
//                .getResultList();
//        if (subCategories.isEmpty()) {
//            return null;
//        }
//        return subCategories;
        Criteria criteria= entityManager.unwrap(Session.class).createCriteria(SubCategory.class);
        if(subCategoryNames!=null && subCategoryNames.size()>0){
            Criterion subCategory = Restrictions.in("name", subCategoryNames);
            criteria.add(subCategory);
        }
        List<SubCategory> categoriesList = criteria.list();
        if(categoriesList.isEmpty()) {
            return null;
        }
        return categoriesList;
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
