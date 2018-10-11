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

import static com.sasya.constant.SasyaConstants.ACTIVE;
import static com.sasya.constant.SasyaConstants.SYSTEM_USER;

@Service
public class UserServiceImpl {


    @Inject
    private UserDAO userDao;

    public void registerUser(String mobile) {
        try {
            Register register = new Register();
            register.setPhone(new BigDecimal(mobile));
            register.setActivationCode("0000");
            register.setCreatedBy("System");
            register.setCreatedDate(SasyaConstants.formatter.format(new Date()));
            userDao.saveUserRegistration(register);
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public SasyaResponse addUser(UserDto user) {
        try {
            Register register = userDao.loadRegister(new BigDecimal(user.getRegister().getMobile()), user.getRegister().getActivationCode());
            if (register != null) {
                userDao.addUserDetails(convertUserDtoToUserModel(user, register));
                SasyaResponse response = new SasyaResponse("Success", "User added Successfully");
                return response;
            }
            SasyaResponse response = new SasyaResponse("Failure", "User Registration not found");
            return response;
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public SasyaResponse login(LoginDto loginDto) {
        try {
            User user = userDao.login(loginDto.getUserName(),loginDto.getPassword());
            if(user!=null) {
                UserDto userDto = convertUserModelToUserDto(user);
                SasyaResponse response = new SasyaResponse("Success", "User details found",userDto);
                return response;
            }
            SasyaResponse response = new SasyaResponse("Failure", "Login Failed");
            return response;
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    private User convertUserDtoToUserModel(UserDto userDto, Register register) {
        User user = new User();
        user.setActive(ACTIVE);
        user.setCreatedBy(SYSTEM_USER);
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
