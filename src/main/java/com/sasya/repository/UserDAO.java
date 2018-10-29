package com.sasya.repository;

import com.sasya.model.Address;
import com.sasya.model.Register;
import com.sasya.model.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

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
     * @param user
     */
    public void updateUserDetails(User user);


    /**
     * @param mobile
     * @param activationCode
     * @return
     */
    public Register loadRegister(BigDecimal mobile, String activationCode);

    /**
     * @param userName
     * @return
     */
    public User login(BigDecimal userName);

    /**
     * @param mobile
     * @return
     */
    public Register findByMobile(BigDecimal mobile);


    /**
     * @param id
     * @return
     */
    public User findUserById(BigDecimal id);

    /**
     * @param mobile
     * @return
     */
    public User loadUser(BigDecimal mobile);

    /**
     * @param address
     * @return
     */
    public void deleteAddress(Address address);


    /**
     * @param userId
     * @param addressId
     * @return
     */
    public Address findAddressById(BigDecimal userId, BigDecimal addressId);

    public List<Address> getAddress(BigDecimal userId, String addressId);

    public <T> void mergeObject(T object);



}
