package com.dgut.orderfoodsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-2-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private Date createTime;
    private Date updateTime;
    private Date loginTime;
    private String phone;
}
