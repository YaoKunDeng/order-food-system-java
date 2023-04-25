package com.dgut.orderfoodsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-4-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineChartData {
    private Date date;
    private Double turnover;
}
