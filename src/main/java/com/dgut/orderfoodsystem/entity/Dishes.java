package com.dgut.orderfoodsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

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
    private Integer id;
    private Integer storeId;
    private Integer menuId;
    private String name;
    private String description;
    private String image;
    private BigDecimal oldPrice;
    private BigDecimal newPrice;
    private Date createTime;
}
