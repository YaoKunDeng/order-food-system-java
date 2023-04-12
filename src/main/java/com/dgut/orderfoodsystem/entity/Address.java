package com.dgut.orderfoodsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;
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
    private String id;
    private String userId;
    private String userName;
    private String addr;
    private String phone;
    private String houseNum;
    private String latitude;
    private String longitude;
    private String addressName;
    private Integer sex;
    private Date createTime;
    private Date updateTime;
}
