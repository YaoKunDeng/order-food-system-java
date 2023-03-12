package com.dgut.orderfoodsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private String id;
    private String storeId;
    private String menuName;
    private String description;
    private Date createTime;
}
