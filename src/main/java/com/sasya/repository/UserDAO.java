package com.sasya.repository;

import com.sasya.model.Register;
import com.sasya.model.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

/**
 * UserDAO
 */
@Repository
@Transactional
public interface UserDAO {

    /**
     * @param register
     */
    public void saveUserRegistration(Register register);

    /**
     * @param user
     */
    public void addUserDetails(User user);

    /**
     * @param mobile
     * @param activationCode
     * @return
     */
    public Register loadRegister(BigDecimal mobile, String activationCode);

    /**
     * @param userName
     * @param password
     * @return
     */
    public User login(BigDecimal userName, String password);

    /**
     * @param mobile
     * @return
     */
    public Register findByMobile(BigDecimal mobile);

    /**
     * @param mobile
     * @return
     */
    public User loadUser(BigDecimal mobile);


}
