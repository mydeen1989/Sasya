package com.sasya.business;

import com.sasya.exception.SasyaException;
import com.sasya.models.Register;
import com.sasya.repositories.RegisterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Response;

public class RegisterImpl {

    @Autowired
    private RegisterDAO registerDAO;

    public void registerUser(Register register) {
        try {
            registerDAO.save(register);
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
