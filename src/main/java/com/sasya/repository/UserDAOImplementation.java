package com.sasya.repository;

import com.sasya.constant.SasyaConstants;
import com.sasya.exception.SasyaException;
import com.sasya.model.Address;
import com.sasya.model.Register;
import com.sasya.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * UserDAOImplementation
 */
@Transactional
@Component
public class UserDAOImplementation implements UserDAO {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * @param register
     */
    public void saveUserRegistration(Register register){
        entityManager.persist(register);
    }

    /**
     * @param user
     */
    @Override
    public void addUserDetails(User user) {
        entityManager.persist(user);
    }

    /**
     * @param mobile
     * @param activationCode
     * @return
     */
    @Override
    public Register loadRegister(BigDecimal mobile, String activationCode) {
        List<Register> lstRegister = entityManager.createQuery("select r from Register r where phone=?1 and activationCode=?2")
                .setParameter(1,mobile)
                .setParameter(2,activationCode)
                .getResultList();
        if(lstRegister.isEmpty()){
            return null;
        }
        return lstRegister.get(0);
    }

    /**
     * @param mobile
     * @param password
     * @return
     */
    @Override
    public User login(BigDecimal mobile, String password) {
       List<User> lstUser = entityManager.createQuery("select u from User u where phone=?1 and password=?2 and active='1'")
               .setParameter(1,mobile)
               .setParameter(2,password)
               .getResultList();
       if(lstUser.isEmpty()){
           return null;
       }
       return lstUser.get(0);
    }

    /**
     * @param mobile
     * @return
     */
    public Register findByMobile(BigDecimal mobile){

        List<Register> lstRegister = entityManager.createQuery("select r from Register r where phone=?1")
                .setParameter(1,mobile)
                .getResultList();
        if(lstRegister.isEmpty()){
            return null;
        }
        return lstRegister.get(0);
    }

    @Override
    public User findUserById(BigDecimal id) {
        try {
            return entityManager.find(User.class, id);
        }
        catch (Exception exp){
            throw new SasyaException(SasyaConstants.USER_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
    }



    /**
     * @param user
     */
    @Override
    public void updateUserDetails(User user) {
        entityManager.merge(user);
    }



    /**
     * @param mobile
     * @return
     */
    public User loadUser(BigDecimal mobile){
        List<User> lstUser = entityManager.createQuery("select u from User u where phone=?1")
                .setParameter(1,mobile)
                .getResultList();
        if(lstUser.isEmpty()){
            return null;
        }
        return lstUser.get(0);
    }

    @Override
    public boolean deleteAddress(BigDecimal userId, BigDecimal addressId) {
        Query updateQuery = entityManager.createNativeQuery("update Address a set a.active=?1 where a.userId=?2 and a.id=?3 and a.active='1'");
        updateQuery.setParameter(1,'0');
        updateQuery.setParameter(2, userId);
        updateQuery.setParameter(3, addressId);
        int result = updateQuery.executeUpdate();
        if(result>0)
            return true;
        return false;
    }

    @Override
    public Address findAddressById(BigDecimal userId, BigDecimal addressId) {
        List<Address> addressList = entityManager.createQuery("from Address a where a.id=?1 and a.userId=?2")
                .setParameter(1, addressId)
                .setParameter(2,userId)
                .getResultList();
        if(addressList.isEmpty()){
            return addressList.get(0);
        }
        return null;
    }

    @Override
    public List<Address> getAddress(BigDecimal userId, List<BigDecimal> addressIds,String type){
        List<Address> addressList = Collections.emptyList();
        if(type.equalsIgnoreCase("ALL")){
            addressList = entityManager.createQuery("from Address a where a.userId=?1").
                    setParameter(1,userId)
                    .getResultList();
        }
        else {
            addressList = entityManager.createQuery("from Address a where a.userId=?1 and a.id in (?2)")
                    .setParameter(1,userId)
                    .setParameter(2,addressIds)
                    .getResultList();
        }
        return addressList;
    }


    @Override
    public <T> void mergeObject(T object) {
        entityManager.merge(object);
    }
}
