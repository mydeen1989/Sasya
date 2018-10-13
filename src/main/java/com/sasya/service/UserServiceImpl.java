package com.sasya.service;

import com.sasya.constant.SasyaConstants;
import com.sasya.dto.LoginDto;
import com.sasya.dto.UserDto;
import com.sasya.exception.SasyaException;
import com.sasya.model.Register;
import com.sasya.model.User;
import com.sasya.repository.UserDAO;
import com.sasya.response.SasyaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


/**
 * UserServiceImpl
 */
@Service
public class UserServiceImpl {


    @Inject
    private UserDAO userDao;



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
            return ResponseEntity.ok().body(SasyaResponse.build(SasyaConstants.SUCCESS, SasyaConstants.REGISTER_SUCCESS));
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @param user
     * @return
     */
    public ResponseEntity addUser(UserDto user) {
        try {
            Register register = userDao.loadRegister(new BigDecimal(user.getRegister().getMobile()), user.getActivationCode());
            if (register == null) {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).
                        body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.MOBILE_NOT_REGISTERED));
            }
            if(userDao.loadUser(new BigDecimal(user.getRegister().getMobile()))!=null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SasyaResponse.build(SasyaConstants.FAILURE, SasyaConstants.USER_ALREADY_REGISTERED));
            }
            userDao.addUserDetails(convertUserDtoToUserModel(user, register));
            return ResponseEntity.ok().body(SasyaResponse.build(SasyaConstants.SUCCESS, SasyaConstants.USER_ADDED_SUCCESSFULLY));
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @param loginDto
     * @return
     */
    public ResponseEntity login(LoginDto loginDto) {
        try {
            User user = userDao.login(new BigDecimal(loginDto.getMobile()),loginDto.getPassword());
            if(user!=null) {
                UserDto userDto = convertUserModelToUserDto(user);
                return ResponseEntity.ok().body(SasyaResponse.build(SasyaConstants.SUCCESS,SasyaConstants.USER_FOUND,userDto));
            }
            return ResponseEntity.ok().body(SasyaResponse.build(SasyaConstants.FAILURE,SasyaConstants.USER_NOT_FOUND));
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @param userDto
     * @param register
     * @return
     */
    private User convertUserDtoToUserModel(UserDto userDto, Register register) {
        User user = new User();
        user.setActive(SasyaConstants.ACTIVE);
        user.setCreatedBy(SasyaConstants.SYSTEM_USER);
        user.setCreatedDate(SasyaConstants.formatter.format(new Date()));
        user.setDeviceId(userDto.getDeviceId());
        user.setDeviceType(userDto.getDeviceType());
        user.setEmail(userDto.getEmail());
        user.setFamilyMembersCount(userDto.getFamilyMembersCount());
        user.setPassword(userDto.getPassword());
        user.setUserName(userDto.getUserName());
        user.setRegisterId(register.getId());
        user.setPhone(register.getPhone());
        return user;
    }


    /**
     * @param user
     * @return
     */
    private UserDto convertUserModelToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setDeviceId(user.getDeviceId());
        userDto.setDeviceType(user.getDeviceType());
        userDto.setEmail(user.getEmail());
        userDto.setFamilyMembersCount(user.getFamilyMembersCount());
        userDto.setUserName(user.getUserName());
        return userDto;
    }

}
