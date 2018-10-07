package com.sasya.business;

import com.sasya.exception.SasyaException;
import com.sasya.models.UserRegister;
import com.sasya.repository.UserRegisterDAO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class UserRegisterImpl {

    @Inject
    private UserRegisterDAO registerDAO;

    public void registerUser(String phone) {
        try {
            UserRegister register = new UserRegister();
            register.setPhone(phone);
            register.setActivationCode("0000");
            register.setCreatedBy("user1");
            registerDAO.save(register);
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
