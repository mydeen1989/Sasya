package com.sasya.service;

import com.sasya.constant.SasyaConstants;
import com.sasya.exception.SasyaException;
import com.sasya.model.Register;
import com.sasya.repository.UserRegisterDAO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class UserServiceImpl {

    @Inject
    private UserRegisterDAO registerDAO;

    public void registerUser(String mobile) {
        try {
            Register register = new Register();
            register.setPhone(new BigDecimal(mobile));
            register.setActivationCode("0000");
            register.setCreatedBy("System");
            register.setCreatedDate(SasyaConstants.formatter.format(new Date()));
            registerDAO.saveUserRegistration(register);
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
