package com.dgut.orderfoodsystem;

import com.dgut.orderfoodsystem.entity.User;
import com.dgut.orderfoodsystem.service.impl.userServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class OrderFoodSystemApplicationTests {
    @Resource
    private userServiceImpl userService;
    @Test
    void contextLoads() {
        User user = new User();
        user.setId(1);
        user.setCreateTime(new Date());
        user.setLoginTime(new Date());
        user.setPassword("123456");
        user.setUsername("123456");
        user.setPhone("123455");
        user.setUpdateTime(new Date());
        userService.addUser(user);
    }

    @Test
    void testGetAllUser(){
        List<User> users = userService.selectAllUser();
        for (User user: users) {
            Date loginTime = user.getLoginTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(loginTime);
            System.out.println(format);
        }
    }

}
