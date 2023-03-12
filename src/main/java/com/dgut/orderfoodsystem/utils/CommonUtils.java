package com.dgut.orderfoodsystem.utils;

import java.util.UUID;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-9
 */
public class CommonUtils {
    public static String generateId(){
        return UUID.randomUUID().toString();
    }
}
