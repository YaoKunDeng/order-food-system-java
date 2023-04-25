package com.dgut.orderfoodsystem.service.impl;

import com.dgut.orderfoodsystem.mapper.SalesDataMapper;
import com.dgut.orderfoodsystem.service.SalesDataService;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import com.dgut.orderfoodsystem.vo.BarGraph;
import com.dgut.orderfoodsystem.vo.LineChartData;
import com.dgut.orderfoodsystem.vo.SalesTable;
import com.dgut.orderfoodsystem.vo.SalesTotal;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-4-24
 */
@Service
public class SalesDataServiceImpl implements SalesDataService {
    @Resource
    private SalesDataMapper salesDataMapper;
    @Override
    public ApiResponse getSalesTable() {
        ApiResponse<List> response = new ApiResponse<>();
        try {
            List<SalesTable> salesTables = salesDataMapper.selectSalesTable();

            response.setMessage("获取salesTables数据成功！");
            response.setCode(200);
            response.setData(salesTables);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.setMessage("获取salesTables数据失败！");
            response.setCode(500);
            return response;
        }
    }

    @Override
    public ApiResponse getSalesTotalData() {

        ApiResponse<SalesTotal> response = new ApiResponse<>();
        try {
            SalesTotal salesTotal = salesDataMapper.selectSalesTotalDate();

            response.setMessage("获取SalesTotal数据成功！");
            response.setCode(200);
            response.setData(salesTotal);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.setMessage("获取ssalesTotal数据失败！");
            response.setCode(500);
            return response;
        }
    }

    @Override
    public ApiResponse getLineChartData() {
        ApiResponse<List> listApiResponse = new ApiResponse<>();
        try {
            List<LineChartData> lineChartData = salesDataMapper.selectLineChartData();
            if(lineChartData==null||lineChartData.size()==0){
                listApiResponse.setMessage("折线图数据为空！");
                listApiResponse.setCode(400);
                return listApiResponse;
            }
            listApiResponse.setMessage("获取折线图数据成功！");
            listApiResponse.setCode(200);
            listApiResponse.setData(lineChartData);
            return listApiResponse;
        }catch (Exception e){
            e.printStackTrace();
            listApiResponse.setMessage("获取折线图数据失败！");
            listApiResponse.setCode(500);
            return listApiResponse;
        }

    }

    @Override
    public ApiResponse getBarGraph() {
        ApiResponse<List> listApiResponse = new ApiResponse<>();
        try {
            List<BarGraph> barGraphs = salesDataMapper.selectBarGraph();
            if(barGraphs==null||barGraphs.size()==0){
                listApiResponse.setMessage("柱形图数据为空！");
                listApiResponse.setCode(400);
                return listApiResponse;
            }
            listApiResponse.setMessage("获取柱形图数据成功！");
            listApiResponse.setCode(200);
            listApiResponse.setData(barGraphs);
            return listApiResponse;
        }catch (Exception e){
            e.printStackTrace();
            listApiResponse.setMessage("获取柱形图数据失败！");
            listApiResponse.setCode(500);
            return listApiResponse;
        }

    }
}
