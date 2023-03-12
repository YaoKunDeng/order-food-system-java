package com.dgut.orderfoodsystem;

import com.dgut.orderfoodsystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-2-27
 */
@SpringBootTest
public class UserTest {

    @Resource
    private UserService userService;

    @Test
    public void testGetUserByUsername(){

    }
}
