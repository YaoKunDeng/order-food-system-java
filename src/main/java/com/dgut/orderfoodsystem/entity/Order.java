package com.dgut.orderfoodsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-2-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String id;
    private String userId;
    private String storeId;
    private Date orderTime;
    private BigDecimal amount;
    private Integer status;
    private String address;
    private Date deliveryTime;
    private String deliveryWay;
    private Date createTime;
    private Date updateTime;
    private String note;
}
