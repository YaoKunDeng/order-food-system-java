package com.dgut.orderfoodsystem.service;

import com.dgut.orderfoodsystem.utils.ApiResponse;
import com.dgut.orderfoodsystem.vo.SalesTable;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-4-24
 */
public interface SalesDataService {

    ApiResponse getSalesTable();

    ApiResponse getSalesTotalData();

    ApiResponse getLineChartData();

    ApiResponse getBarGraph();
}
