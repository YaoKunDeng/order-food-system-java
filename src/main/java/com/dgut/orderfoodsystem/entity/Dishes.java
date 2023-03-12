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
public class Dishes {
    private String id;
    private String storeId;
    private String menuId;
    private String dishName;
    private String description;
    private String image;
    private BigDecimal oldPrice;
    private BigDecimal newPrice;
    private Date createTime;
}
