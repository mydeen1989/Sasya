package com.sasya.repository;

import com.sasya.model.Register;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Component
public class UserRegisterImplmentation implements UserRegisterDAO{

    @PersistenceContext
    EntityManager entityManager;

    public void saveUserRegistration(Register register){
        entityManager.persist(register);
    }

}
