package com.sasya.repository;

import com.sasya.model.Register;
import com.sasya.model.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Repository
@Transactional
public interface UserDAO {

    public void saveUserRegistration(Register register);

    public void addUserDetails(User user);

    public Register loadRegister(BigDecimal mobile, String activationCode);

    public User login(String userName,String password);

}
