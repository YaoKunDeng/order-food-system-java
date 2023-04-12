package com.dgut.orderfoodsystem.service.impl;

import com.dgut.orderfoodsystem.entity.Orders;
import com.dgut.orderfoodsystem.mapper.OrderMapper;
import com.dgut.orderfoodsystem.service.OrderService;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import com.dgut.orderfoodsystem.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-4-9
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Override
    public ApiResponse addOrder(Orders orders) {
        ApiResponse<Orders> orderApiResponse = new ApiResponse<>();
        if(orders ==null){
            orderApiResponse.setCode(400);
            orderApiResponse.setMessage("order为空！");
            return orderApiResponse;
        }
        if(StringUtils.isBlank(orders.getUserId())){
            orderApiResponse.setCode(400);
            orderApiResponse.setMessage("userId为空！");
            return orderApiResponse;
        }
        if(StringUtils.isBlank(orders.getStoreId())){
            orderApiResponse.setCode(400);
            orderApiResponse.setMessage("storeId为空！");
            return orderApiResponse;
        }
        try {
            orders.setId(CommonUtils.generateId());
            orders.setOrderTime(new Date());
            orders.setStatus(0);
            orders.setDeliveryTime(new Date(System.currentTimeMillis()+60*1000*30));
            orders.setCreateTime(new Date());
            orders.setUpdateTime(new Date());
            orderMapper.insertOrder(orders);
            orderApiResponse.setCode(200);
            orderApiResponse.setMessage("插入成功！");
            orderApiResponse.setData(orders);
            return orderApiResponse;
        }catch (Exception e){
            e.printStackTrace();
            orderApiResponse.setMessage("插入失败！");
            orderApiResponse.setCode(500);
            orderApiResponse.setData(orders);
            return orderApiResponse;
        }
    }

    @Override
    public ApiResponse getOrderByUserId(String userId) {
        ApiResponse<List> listApiResponse = new ApiResponse<>();
        if(StringUtils.isBlank(userId)){
            listApiResponse.setCode(400);
            listApiResponse.setMessage("userId为空！");
            return listApiResponse;
        }
        try {
            List<Orders> orders = orderMapper.selectOrdersByUserId(userId);
            listApiResponse.setCode(200);
            listApiResponse.setData(orders);
            listApiResponse.setMessage("获取用户订单列表成功！");
            if(orders.size()==0||orders==null){
                listApiResponse.setMessage("该用户的订单列表为空！");
            }
            return listApiResponse;
        }catch (Exception e){
            e.printStackTrace();
            listApiResponse.setCode(500);
            listApiResponse.setMessage("获取订单列表失败！");
            return listApiResponse;
        }

    }
}
