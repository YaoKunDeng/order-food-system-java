package com.dgut.orderfoodsystem;

import com.dgut.orderfoodsystem.service.impl.SalesDataServiceImpl;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-4-24
 */
@SpringBootTest
public class SalesDataTest {
    @Resource
    private SalesDataServiceImpl salesDataService;

    @Test
    public void testSalesTable(){
        ApiResponse salesTable = salesDataService.getSalesTable();
        System.out.println(salesTable);
    }

    @Test
    public void testSalesTotal(){
        ApiResponse salesTotalData = salesDataService.getSalesTotalData();
        System.out.println(salesTotalData);
    }

    @Test
    public void testLineChartData(){
        ApiResponse lineChartData = salesDataService.getLineChartData();
        System.out.println(lineChartData);
    }

    @Test
    public void testBarGraph(){
        ApiResponse barGraph = salesDataService.getBarGraph();
        System.out.println(barGraph);
    }

}
