package com.dgut.orderfoodsystem.service;

import com.dgut.orderfoodsystem.entity.User;

import java.util.List;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-2-25
 */
public interface UserService {
    void addUser(User user);

    List<User> selectAllUser();
}
