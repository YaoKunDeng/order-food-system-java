package com.dgut.orderfoodsystem.controller;

import com.dgut.orderfoodsystem.service.impl.SalesDataServiceImpl;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-4-24
 */
@RestController
@RequestMapping("/sales")
public class SalesDataController {
    @Resource
    private SalesDataServiceImpl salesDataService;

    @RequestMapping(path = "/table",method = RequestMethod.GET)
    public ApiResponse getSalesTable(){
        ApiResponse salesTable = salesDataService.getSalesTable();
        System.out.println(salesTable);
        return salesTable;
    }

    @RequestMapping(path = "/total", method = RequestMethod.GET)
    public ApiResponse getSalesTotal(){
        ApiResponse salesTotalData = salesDataService.getSalesTotalData();
        return salesTotalData;
    }

    @RequestMapping(path = "/line/chart", method = RequestMethod.GET)
    public ApiResponse getLineChartData(){
        ApiResponse lineChartData = salesDataService.getLineChartData();
        return lineChartData;
    }

    @RequestMapping(path = "/bar/graph",method = RequestMethod.GET)
    public ApiResponse getBarGraphData(){
        ApiResponse barGraph = salesDataService.getBarGraph();
        return barGraph;
    }
}
