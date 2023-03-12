package com.dgut.orderfoodsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-2-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginTicket {
    private String id;
    private String userId;
    private String ticket; //uuid  盐值
    private Integer status;  //status 登录的状态  0-有效   1-无效
    private Date expired;
    private Integer type;   //0-微信登录  1-web登录
}
