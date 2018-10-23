package com.sasya.constant;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * SasyaConstants
 */
public class SasyaConstants {

    public static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    public static final String SYSTEM_USER = "System";
    public static final String ACTIVE = "1";
    public static final String SUCCESS = "Success";
    public static final String FAILURE = "Failure";
    public static final String OK = "OK";
    public static final String USER_ADDED_SUCCESSFULLY = "User added Successfully";
    public static final String USER_ALREADY_REGISTERED = "User already registered";
    public static final String MOBILE_REGISTER_FAILURE = "Mobile number already registered";
    public static final String USER_NOT_FOUND = "User Not found";
    public static final String USER_FOUND = "User details found";
    public static final String LOGIN_FAILED = "Login Failed";
    public static final String REGISTER_SUCCESS = "Mobile number Registered";
    public static final String MOBILE_NOT_REGISTERED = "Mobile number not registered";
    public static final String INACTIVE = "0";
    public static final String OTP_FAILURE = "OTP invalid";

    /************* Address Messages ********************/
    public static final String ADDRESS_DELETE_SUCCESS = "Address removed successfully";
    public static final String ADDRESS_ADD_SUCCESS = "User address added";
    public static final String ADDRESS_NOT_FOUND = "Address not found";
    public static final String ADDRESS_UPDATE_SUCCESS = "Address updated successfully";
}
