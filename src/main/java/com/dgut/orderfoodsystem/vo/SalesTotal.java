package com.dgut.orderfoodsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-4-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesTotal {
    private Double todaySales;
    private Double monthSales;
    private Double totalSales;
}
