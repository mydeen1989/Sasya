package com.sasya.util;

import com.sasya.constant.SasyaConstants;
import com.sasya.dto.AddressDto;
import com.sasya.dto.UserDto;
import com.sasya.model.Address;
import com.sasya.model.Register;
import com.sasya.model.User;

import java.math.BigDecimal;
import java.util.Date;

public class SasyaUtils {

    /**
     * @param userDto
     * @param register
     * @return
     */
    public static User convertUserDtoToUserModel(UserDto userDto, Register register) {
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
    public static UserDto convertUserModelToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setDeviceId(user.getDeviceId());
        userDto.setDeviceType(user.getDeviceType());
        userDto.setEmail(user.getEmail());
        userDto.setFamilyMembersCount(user.getFamilyMembersCount());
        userDto.setUserName(user.getUserName());
        return userDto;
    }

    public static Address convertAddressDtoToEntity(AddressDto addressDto,User user) {
        Address address = new Address();
        address.setAddress(addressDto.getAddress());
        address.setAddressType(addressDto.getAddressType());
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setLandmark(addressDto.getLandmark());
        address.setPincode(new BigDecimal(addressDto.getPincode()));
        address.setSecondaryMobile(addressDto.getSecondary_mobile());
        address.setState(addressDto.getState());
        address.setCreatedDate(SasyaConstants.formatter.format(new Date()));
        address.setCreatedBy(user.getUserName());
        address.setUser(user);
        return address;
    }



}
