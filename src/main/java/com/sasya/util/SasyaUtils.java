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

    public static Double getNetPrice(Double price, int discountPercent, int gstPercent){

        double discountPrice =0, gstPrice = 0;
        if(discountPercent >0){
            discountPrice = discountPercent*price/100;
        }
        if(gstPercent>0){
            gstPrice = gstPercent*price/100;
        }
        return price-discountPrice+gstPrice;
    }


}
