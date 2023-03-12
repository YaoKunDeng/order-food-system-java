package com.dgut.orderfoodsystem.service;

import com.dgut.orderfoodsystem.entity.LoginTicket;
import com.dgut.orderfoodsystem.utils.ApiResponse;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-3
 */
public interface LoginTicketService {
    void addLoginTicket(String userId,String sessionKey, int expired, int type);

    LoginTicket getLoginTicketByTicket(String ticket);

    void updateLoginTicket(String ticket, int status);
}
