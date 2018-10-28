package com.sasya.repository;

import com.sasya.model.Category;
import com.sasya.model.Product;
import com.sasya.model.SubCategory;
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
    public List<Product> getAllProducts(BigDecimal categoryId, BigDecimal subCategoryId, String popularity) {

        List<Product> products = new ArrayList<>();
        products = filterByCategory(categoryId, subCategoryId, popularity, products);
        products = filterByCategoryAndSubCategory(categoryId, subCategoryId, popularity, products);
        products = filterByCategoryAndPopularity(categoryId, subCategoryId, popularity, products);
        products = filterByCategoryAndSubCategoryAndPopularity(categoryId, subCategoryId, popularity, products);
        products = filterBySubCategory(categoryId, subCategoryId, popularity, products);
        products = filterBySubCategoryAndPopularity(categoryId, subCategoryId, popularity, products);
        products = filterByPopularity(categoryId, subCategoryId, popularity, products);
        products = getAllProducts(categoryId, subCategoryId, popularity, products);
        if (products.isEmpty()) {
            return null;
        }
        return products;
    }

    /**
     * @param categoryId
     * @param subCategoryId
     * @param popularity
     * @param products
     * @return
     */
    private List<Product> getAllProducts(BigDecimal categoryId, BigDecimal subCategoryId, String popularity, List<Product> products) {
        if(categoryId == null && subCategoryId == null && popularity == null) {
            products = entityManager.createQuery("select p from Product p where p.active='1'")
                    .getResultList();
        }
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


    /**
     * @param categoryId
     * @param subCategoryId
     * @param popularity
     * @param products
     * @return
     */
    private List<Product> filterByPopularity(BigDecimal categoryId, BigDecimal subCategoryId, String popularity, List<Product> products) {
        if(categoryId ==null && subCategoryId ==null && popularity !=null ){
            products = entityManager.createQuery("select p from Product p where p.active='1' and p.popularity=?1")
                    .setParameter(1,popularity)
                    .getResultList();
        }
        return products;
    }

    /**
     * @param categoryId
     * @param subCategoryId
     * @param popularity
     * @param products
     * @return
     */
    private List<Product> filterBySubCategoryAndPopularity(BigDecimal categoryId, BigDecimal subCategoryId, String popularity, List<Product> products) {
        if(categoryId ==null && subCategoryId !=null && popularity !=null ){
            products = entityManager.createQuery("select p from Product p where p.active='1'  and p.subCategoryId=?1  and p.popularity=?2")
                    .setParameter(1,subCategoryId)
                    .setParameter(2,popularity)
                    .getResultList();
        }
        return products;
    }

    /**
     * @param categoryId
     * @param subCategoryId
     * @param popularity
     * @param products
     * @return
     */
    private List<Product> filterBySubCategory(BigDecimal categoryId, BigDecimal subCategoryId, String popularity, List<Product> products) {
        if(categoryId ==null && subCategoryId !=null && popularity ==null ){
            products = entityManager.createQuery("select p from Product p where p.active='1'  and p.subCategoryId=?1")
                    .setParameter(1,subCategoryId)
                    .getResultList();
        }
        return products;
    }

    /**
     * @param categoryId
     * @param subCategoryId
     * @param popularity
     * @param products
     * @return
     */
    private List<Product> filterByCategoryAndSubCategoryAndPopularity(BigDecimal categoryId, BigDecimal subCategoryId, String popularity, List<Product> products) {
        if(categoryId !=null && subCategoryId !=null && popularity !=null ){
            products = entityManager.createQuery("select p from Product p where p.active='1' and p.categoryId=?1 and p.subCategoryId=?2 and p.popularity=?3")
                    .setParameter(1,categoryId)
                    .setParameter(2,subCategoryId)
                    .setParameter(3,popularity)
                    .getResultList();
        }
        return products;
    }

    /**
     * @param categoryId
     * @param subCategoryId
     * @param popularity
     * @param products
     * @return
     */
    private List<Product> filterByCategoryAndPopularity(BigDecimal categoryId, BigDecimal subCategoryId, String popularity, List<Product> products) {
        if(categoryId !=null && subCategoryId ==null && popularity !=null ){
            products = entityManager.createQuery("select p from Product p where p.active='1' and p.categoryId=?1 and p.popularity=?2")
                    .setParameter(1,categoryId)
                    .setParameter(2,popularity)
                    .getResultList();
        }
        return products;
    }

    /**
     * @param categoryId
     * @param subCategoryId
     * @param popularity
     * @param products
     * @return
     */
    private List<Product> filterByCategoryAndSubCategory(BigDecimal categoryId, BigDecimal subCategoryId, String popularity, List<Product> products) {
        if(categoryId !=null && subCategoryId !=null && popularity ==null ){
            products = entityManager.createQuery("select p from Product p where p.active='1' and p.categoryId=?1 and p.subCategoryId=?2")
                    .setParameter(1,categoryId)
                    .setParameter(2,subCategoryId)
                    .getResultList();
        }
        return products;
    }

    /**
     * @param categoryId
     * @param subCategoryId
     * @param popularity
     * @param products
     * @return
     */
    private List<Product> filterByCategory(BigDecimal categoryId, BigDecimal subCategoryId, String popularity, List<Product> products) {
        if(categoryId !=null && subCategoryId== null && popularity == null){
            products = entityManager.createQuery("select p from Product p where p.active='1' and p.categoryId=?1")
                    .setParameter(1,categoryId)
                    .getResultList();
        }
        return products;
    }

}
