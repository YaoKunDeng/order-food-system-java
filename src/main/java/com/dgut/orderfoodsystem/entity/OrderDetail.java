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
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    private Integer id;
    private Integer orderId;
    private Integer dishId;
    private String name;
    private Integer num;
    private BigDecimal price;
    private String image;
    private Date createTime;
    private Date updateTime;
}
