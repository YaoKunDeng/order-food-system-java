package com.dgut.orderfoodsystem.mapper;

import com.dgut.orderfoodsystem.entity.LoginTicket;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-3
 */
@Mapper
@Repository
public interface LoginTicketMapper {
    void addLoginTicket(LoginTicket loginTicket);

    LoginTicket selectLoginTicketByTicket(String ticket);

    void updateLoginTicket(String ticket,int status);
}
