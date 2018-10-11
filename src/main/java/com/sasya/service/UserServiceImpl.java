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
import org.springframework.stereotype.Service;

import javax.inject.Inject;
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
    public SasyaResponse registerUser(String mobile) {
        try {
                if(!Objects.nonNull(userDao.findByMobile(new BigDecimal(mobile)))) {
                    Register register = new Register();
                    register.setPhone(new BigDecimal(mobile));
                    register.setActivationCode("0000");
                    register.setCreatedBy(SasyaConstants.SYSTEM_USER);
                    register.setCreatedDate(SasyaConstants.formatter.format(new Date()));
                    userDao.saveUserRegistration(register);
                    return new SasyaResponse(SasyaConstants.SUCCESS, SasyaConstants.OK);
                }
                else{
                    return new SasyaResponse(SasyaConstants.FAILURE, SasyaConstants.MOBILE_REGISTERED);
                }

        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @param user
     * @return
     */
    public SasyaResponse addUser(UserDto user) {
        try {
            Register register = userDao.loadRegister(new BigDecimal(user.getRegister().getMobile()), user.getActivationCode());
            if (register != null) {
                User userDetails = userDao.loadUser(register.getPhone());
                if(!Objects.nonNull(userDetails)) {
                    userDao.addUserDetails(convertUserDtoToUserModel(user, register));
                    return new SasyaResponse(SasyaConstants.SUCCESS, SasyaConstants.USER_ADDED_SUCCESSFULLY);
                }
                else{
                    return new SasyaResponse(SasyaConstants.FAILURE, SasyaConstants.USER_REGISTERED);
                }
            }
            throw new SasyaException(SasyaConstants.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @param loginDto
     * @return
     */
    public SasyaResponse login(LoginDto loginDto) {
        try {
            User user = userDao.login(loginDto.getUserName(),loginDto.getPassword());
            if(user!=null) {
                UserDto userDto = convertUserModelToUserDto(user);
                SasyaResponse response = new SasyaResponse(SasyaConstants.SUCCESS, SasyaConstants.USER_FOUND,userDto);
                return response;
            }
            return new SasyaResponse(SasyaConstants.FAILURE, SasyaConstants.LOGIN_FAILED);
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
