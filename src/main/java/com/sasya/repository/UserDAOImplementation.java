package com.sasya.repository;

import com.sasya.model.Register;
import com.sasya.model.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Component
public class UserDAOImplementation implements UserDAO {

    @PersistenceContext
    EntityManager entityManager;

    public void saveUserRegistration(Register register){
        entityManager.persist(register);
    }

    @Override
    public void addUserDetails(User user) {
        entityManager.persist(user);
    }

    @Override
    public Register loadRegister(BigDecimal mobile, String activationCode) {
        List<Register> lstRegister = entityManager.createQuery("select r from RegisterDto r where phone=?1 and activationCode=?2")
                .setParameter(1,mobile)
                .setParameter(2,activationCode)
                .getResultList();
        if(lstRegister.isEmpty()){
            return null;
        }
        return lstRegister.get(0);
    }

    @Override
    public User login(String userName, String password) {
       List<User> lstUser = entityManager.createQuery("select u from User u where userName=?1 and password=?2 and active='1'")
               .setParameter(1,userName)
               .setParameter(2,password)
               .getResultList();
       if(lstUser.isEmpty()){
           return null;
       }
       return lstUser.get(0);
    }



}
