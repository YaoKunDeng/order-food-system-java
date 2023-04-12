package com.dgut.orderfoodsystem.service;

import com.dgut.orderfoodsystem.entity.Orders;
import com.dgut.orderfoodsystem.utils.ApiResponse;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-4-9
 */
public interface OrderService {
    ApiResponse addOrder(Orders orders);

    ApiResponse getOrderByUserId(String userId);
}
