package com.sasya.service;

import com.sasya.constant.SasyaConstants;
import com.sasya.dto.AddressDto;
import com.sasya.dto.IResponseDto;
import com.sasya.dto.LoginDto;
import com.sasya.dto.UserDto;
import com.sasya.exception.SasyaException;
import com.sasya.model.Address;
import com.sasya.model.Register;
import com.sasya.model.User;
import com.sasya.repository.UserDAO;
import com.sasya.response.SasyaResponse;
import com.sasya.util.SasyaUtils;
import com.sasya.util.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * UserServiceImpl
 */
@Service
public class UserServiceImpl {


    @Inject
    private UserDAO userDao;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * @param mobile
     * @return
     */
    public ResponseEntity registerUser(String mobile) {
        try {
            if (Objects.nonNull(userDao.findByMobile(new BigDecimal(mobile)))) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.MOBILE_REGISTER_FAILURE));
            }
            Register register = new Register();
            register.setPhone(new BigDecimal(mobile));
            register.setActivationCode("0000");
            register.setCreatedBy(SasyaConstants.SYSTEM_USER);
            register.setCreatedDate(SasyaConstants.formatter.format(new Date()));
            userDao.saveUserRegistration(register);
            return ResponseEntity.status(HttpStatus.OK).body(SasyaResponse.build(SasyaConstants.SUCCESS, SasyaConstants.REGISTER_SUCCESS));
        } catch (Exception exp) {
            logger.error("registerUser method failed",exp);
            throw new SasyaException("REGISTRATION FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @param user
     * @return
     */
    public ResponseEntity addUser(UserDto user) {
        try {
            Register register = userDao.loadRegister(new BigDecimal(user.getRegister().getMobile()), user.getOtp());
            if (register == null) {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).
                        body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.MOBILE_NOT_REGISTERED));
            }

            if (register.getUser() != null && register.getUser().getPhone() != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.USER_ALREADY_REGISTERED));
            }
            userDao.addUserDetails(Mapper.convertUserDtoToUserModel(user, register));
            return ResponseEntity.status(HttpStatus.OK).body(SasyaResponse.build(SasyaConstants.SUCCESS, SasyaConstants.USER_ADDED_SUCCESSFULLY));
        } catch (Exception exp) {
            logger.error("addUser method failed", exp);
            throw new SasyaException("ADD USER FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @param loginDto
     * @return
     */
    public ResponseEntity login(LoginDto loginDto) {
        try {
            if (loginDto.getOtp() != null && loginDto.getOtp().equals("0000")) {
                User user = userDao.login(new BigDecimal(loginDto.getMobile()));
                if (user != null) {
                    UserDto userDto = Mapper.convertUserModelToUserDto(user);
                    return ResponseEntity.status(HttpStatus.OK).body(SasyaResponse.build(SasyaConstants.SUCCESS, SasyaConstants.USER_FOUND, Collections.singletonList(userDto)));
                }
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.USER_NOT_FOUND));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.OTP_FAILURE));
        } catch (Exception exp) {
            logger.error("Login Method FAILED",exp);
            throw new SasyaException("LOGIN FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param addressDto
     * @return
     */
    public ResponseEntity addAddress(AddressDto addressDto, BigDecimal userId) {
        try {
            User user = userDao.findUserById(userId);
            if (Objects.nonNull(user)) {
                Address address = Mapper.convertAddressDtoToEntity(addressDto, user);
                user.getAddress().add(address);
                userDao.updateUserDetails(user);
                return ResponseEntity.status(HttpStatus.CREATED).body(SasyaResponse.build(SasyaConstants.SUCCESS, SasyaConstants.ADDRESS_ADD_SUCCESS));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.USER_NOT_FOUND));
        } catch (SasyaException sasyaExp) {
            throw sasyaExp;
        } catch (Exception exp) {
            logger.error("add address failed", exp);
            throw new SasyaException("ADD ADDRESS FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param userId
     * @param addressId
     * @return
     */
    public ResponseEntity deleteAddress(BigDecimal userId, BigDecimal addressId) {
        try {
            if (userDao.deleteAddress(userId, addressId)) {
                return ResponseEntity.status(HttpStatus.OK).body(SasyaResponse.build(SasyaConstants.SUCCESS, SasyaConstants.ADDRESS_DELETE_SUCCESS));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.ADDRESS_NOT_FOUND));
        } catch (SasyaException sasyaExp) {
            throw sasyaExp;
        } catch (Exception exp) {
            logger.error("deleteAddress method failed", exp);
            throw new SasyaException("DELETE ADDRESS FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param addressDto
     * @return
     */
    public ResponseEntity updateAddress(BigDecimal userId, BigDecimal addressId, AddressDto addressDto) {
        try {
            Address address = userDao.findAddressById(userId, addressId);
            if (address == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.ADDRESS_NOT_FOUND));
            }
            Mapper.convertAddressDtoToEntity(addressDto, address);
            address.setUpdatedBy(userId.toPlainString());
            address.setUpdatedDate(SasyaConstants.formatter.format(new Date()));
            userDao.mergeObject(address);
            return ResponseEntity.status(HttpStatus.OK).body(SasyaResponse.build(SasyaConstants.SUCCESS, SasyaConstants.ADDRESS_UPDATE_SUCCESS));
        } catch (SasyaException sasyaExp) {
            throw sasyaExp;
        } catch (Exception exp) {
            logger.error("update address failed",exp);
            throw new SasyaException("UPDATE ADDRESS FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param
     * @return
     */
    public ResponseEntity getAddress(BigDecimal userId, List<BigDecimal> addressIds, String type) {
        try {
            List<AddressDto> addressList = userDao.getAddress(userId, addressIds, type).
                    stream().map(Mapper::convertAddressEntityToDto).collect(Collectors.toList());
            return ResponseEntity.ok().body(SasyaResponse.build(SasyaConstants.SUCCESS,"",addressList));
        } catch (SasyaException sasyaExp){
            throw sasyaExp;
        }
        catch (Exception exp) {
            logger.error("getAddress failed",exp);
            throw new SasyaException("GET ADDRESS FAILED",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
