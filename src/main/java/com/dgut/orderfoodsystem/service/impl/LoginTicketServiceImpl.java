package com.dgut.orderfoodsystem.service.impl;

import com.dgut.orderfoodsystem.entity.LoginTicket;
import com.dgut.orderfoodsystem.mapper.LoginTicketMapper;
import com.dgut.orderfoodsystem.service.LoginTicketService;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-3
 */
@Service
public class LoginTicketServiceImpl implements LoginTicketService {
    @Resource
    private LoginTicketMapper loginTicketMapper;
    @Override
    public void addLoginTicket(String userId,String sessionKey, int expired,int type) {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setId(UUID.randomUUID().toString());
        loginTicket.setUserId(userId);
        loginTicket.setStatus(0);
        loginTicket.setType(type);
        loginTicket.setExpired(new Date(System.currentTimeMillis()+expired*60*60*1000));
        //web端的ticket是自己使用UUID生成的，小程序端是微信服务器生成的
        if(StringUtils.isBlank(sessionKey)){
            loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("-",""));
        }else {
            loginTicket.setTicket(sessionKey);
        }
        loginTicketMapper.addLoginTicket(loginTicket);
    }

    @Override
    public LoginTicket getLoginTicketByTicket(String ticket) {
        LoginTicket loginTicket = loginTicketMapper.selectLoginTicketByTicket(ticket);
        return loginTicket;
    }

    @Override
    public void updateLoginTicket(String ticket, int status) {
        loginTicketMapper.updateLoginTicket(ticket,status);
    }
}
