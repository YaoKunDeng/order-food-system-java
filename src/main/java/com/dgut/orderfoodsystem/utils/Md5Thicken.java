package com.dgut.orderfoodsystem.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-2-27
 */
public class Md5Thicken {
    //生成随机
    public static String generateUUID(){
        String s = UUID.randomUUID().toString().replaceAll("-","");
        return s;
    }

    //MD5加密
    public static String MD5(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }


    public static void main(String[] args){
        String s = generateUUID();
        System.out.println(s);
    }

}
