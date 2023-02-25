package com.dgut.orderfoodsystem.service.impl;

import com.dgut.orderfoodsystem.entity.User;
import com.dgut.orderfoodsystem.mapper.UserMapper;
import com.dgut.orderfoodsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-2-25
 */
@Service
public class userServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public void addUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public List<User> selectAllUser() {
        List<User> users = userMapper.selectAllUser();

        return users;
    }
}
