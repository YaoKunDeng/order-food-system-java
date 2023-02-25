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
public class Address {
    private Integer id;
    private Integer userId;
    private String address;
    private Date createTime;
    private Date updateTime;

}
