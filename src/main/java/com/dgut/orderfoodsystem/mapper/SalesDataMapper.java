package com.dgut.orderfoodsystem.mapper;

import com.dgut.orderfoodsystem.vo.BarGraph;
import com.dgut.orderfoodsystem.vo.LineChartData;
import com.dgut.orderfoodsystem.vo.SalesTable;
import com.dgut.orderfoodsystem.vo.SalesTotal;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-4-24
 */
@Mapper
@Repository
public interface SalesDataMapper {
    List<SalesTable> selectSalesTable();

    SalesTotal selectSalesTotalDate();

    List<LineChartData> selectLineChartData();

    List<BarGraph> selectBarGraph();
}
