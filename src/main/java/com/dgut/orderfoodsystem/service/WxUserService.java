package com.dgut.orderfoodsystem.service;

import com.dgut.orderfoodsystem.entity.WxUser;
import com.dgut.orderfoodsystem.utils.ApiResponse;

import java.util.List;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-3
 */
public interface WxUserService {
    ApiResponse register(WxUser wxUser);

    WxUser selectUserByOpenId(String openId);

    ApiResponse getWxUsers();

    ApiResponse delWxUser(String openId);

}
