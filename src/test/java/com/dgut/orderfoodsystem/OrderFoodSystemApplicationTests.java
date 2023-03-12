package com.dgut.orderfoodsystem;

import com.dgut.orderfoodsystem.entity.User;
import com.dgut.orderfoodsystem.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;


@SpringBootTest
class OrderFoodSystemApplicationTests {
    @Resource
    private UserServiceImpl userService;
    @Test
    void contextLoads() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setCreateTime(new Date());
        user.setLoginTime(new Date());
        user.setPassword("123456");
        user.setUsername("123456");
        user.setPhone("123455");
        user.setUpdateTime(new Date());

    }



}
