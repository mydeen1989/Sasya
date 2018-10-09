package com.sasya.repository;

import com.sasya.model.Register;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRegisterDAO {

    public void saveUserRegistration(Register register);

}
