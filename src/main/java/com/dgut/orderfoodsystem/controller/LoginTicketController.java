package com.dgut.orderfoodsystem.controller;

import com.dgut.orderfoodsystem.entity.LoginTicket;
import com.dgut.orderfoodsystem.service.LoginTicketService;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-7
 */
@RestController
public class LoginTicketController {
    @Resource
    private LoginTicketService loginTicketService;

    //验证token是否过期
    @RequestMapping(path = "expired",method = RequestMethod.GET)
    public ApiResponse authentic(@RequestHeader("token") String token){
        System.out.println("token："+token);
        ApiResponse<Map> mapApiResponse = new ApiResponse<>();
        if(StringUtils.isBlank(token)){
            mapApiResponse.setMessage("未登录");
            mapApiResponse.setCode(400);
            return mapApiResponse;
        }
        LoginTicket loginTicketByTicket = loginTicketService.getLoginTicketByTicket(token);
        if(loginTicketByTicket==null){
            mapApiResponse.setCode(400);
            mapApiResponse.setMessage("token过期，请重新登录");
            return mapApiResponse;
        }
        Date expired = loginTicketByTicket.getExpired();
        int flag = expired.compareTo(new Date());
        if(flag==-1){
            mapApiResponse.setCode(400);
            mapApiResponse.setMessage("token过期了！");
            return mapApiResponse;
        }
        mapApiResponse.setCode(200);
        mapApiResponse.setMessage("token有效！");
        return mapApiResponse;
    }
}
