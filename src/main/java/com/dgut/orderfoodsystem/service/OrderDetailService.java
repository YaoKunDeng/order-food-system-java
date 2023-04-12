package com.dgut.orderfoodsystem.service;

import com.dgut.orderfoodsystem.entity.OrderDetail;
import com.dgut.orderfoodsystem.utils.ApiResponse;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-19
 */
public interface OrderDetailService {
    ApiResponse addOrderDetail(OrderDetail orderDetail);

    ApiResponse getOrderDetailByOrderId(String orderId);
}
