package com.sasya.util;

import java.util.Collection;
import java.util.stream.Stream;

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

    public static <T> Stream<T> getEmptyStreamOnNull(Collection<T> collection){
        return collection!=null ? collection.stream() : Stream.empty();
    }


}
