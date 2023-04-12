package com.dgut.orderfoodsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    private String id;
    private String menuId;
    private String dishId;
    private String shoppingNumber;
    private String imgURL;
    private String dishName;
    private String description;
    private Integer num;
    private BigDecimal oldPrice;
    private BigDecimal newPrice;
    private Date createTime;
}
