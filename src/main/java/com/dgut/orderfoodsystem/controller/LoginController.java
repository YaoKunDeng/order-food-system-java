package com.dgut.orderfoodsystem.controller;
import com.dgut.orderfoodsystem.entity.User;
import com.dgut.orderfoodsystem.service.LoginTicketService;
import com.dgut.orderfoodsystem.service.UserService;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-2-27
 */
@RestController
public class LoginController {

    @Resource
    private UserService userService;
    @Resource
    private LoginTicketService loginTicketService;

    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public ApiResponse register(User user){
        ApiResponse register = userService.register(user);
        return  register;
    }

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public ApiResponse login(String username, String password){
        Map<String, Object> map = new HashMap<>();
        ApiResponse response = userService.login(username,password);
        if(response.getCode()!=200){
            return response;
        }
        User user = (User) response.getData();
        //生成session登录凭证
        String sessionKey = UUID.randomUUID().toString().replaceAll("-", "");
        loginTicketService.addLoginTicket(user.getId(),sessionKey,1,1);
        long expired= System.currentTimeMillis()+60*60*24*1000;
        map.put("token",sessionKey);
        map.put("expired",expired);
        user.setPassword("");
        map.put("userInfo",user);
        response.setData(map);
        return response;
    }
}
