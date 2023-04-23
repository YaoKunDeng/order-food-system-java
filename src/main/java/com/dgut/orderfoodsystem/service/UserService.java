package com.dgut.orderfoodsystem.service;
import com.dgut.orderfoodsystem.entity.User;
import com.dgut.orderfoodsystem.utils.ApiResponse;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-2-25
 */
public interface UserService {
    ApiResponse register(User user);

    ApiResponse login(String username, String password);

    ApiResponse updateUser(User user);


}
