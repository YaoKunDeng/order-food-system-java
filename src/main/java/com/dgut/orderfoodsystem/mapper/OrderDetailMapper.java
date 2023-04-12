package com.dgut.orderfoodsystem.mapper;

import com.dgut.orderfoodsystem.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-19
 */
@Mapper
@Repository
public interface OrderDetailMapper {
    void addOrderDetail(OrderDetail orderDetail);

    List<OrderDetail> selectOrderDetailByOrderId(String orderId);
}
