package com.dgut.orderfoodsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-2-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class menu {
    private Integer id;
    private Integer storeId;
    private String name;
    private String description;
}
