package com.rozan.higor.orderapp.utils;

/**
 * Created by Higor on 21/11/2017.
 */

public class StringUtils {

    public static boolean emptyString(String str){
        if (str == null || str.trim().length() == 0){
            return true;
        }else{
            return false;
        }
    }

}
