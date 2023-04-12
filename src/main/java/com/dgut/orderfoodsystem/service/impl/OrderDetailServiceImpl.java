package com.dgut.orderfoodsystem.service.impl;

import com.dgut.orderfoodsystem.entity.OrderDetail;
import com.dgut.orderfoodsystem.mapper.OrderDetailMapper;
import com.dgut.orderfoodsystem.service.OrderDetailService;
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
 * @Data 2023-3-19
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Resource
    private OrderDetailMapper orderDetailMapper;
    @Override
    public ApiResponse<OrderDetail> addOrderDetail(OrderDetail orderDetail) {
        ApiResponse<OrderDetail> apiResponse = new ApiResponse<>();
        if(orderDetail==null){
            apiResponse.setCode(400);
            apiResponse.setMessage("OrderDetail为空！");
            return apiResponse;
        }
        try{
            orderDetail.setId(CommonUtils.generateId());
            orderDetail.setCreateTime(new Date());
            orderDetailMapper.addOrderDetail(orderDetail);
            apiResponse.setCode(200);
            apiResponse.setData(orderDetail);
            apiResponse.setMessage("插入orderDetail成功！");
            return apiResponse;
        }catch (Exception e){
            e.printStackTrace();
            apiResponse.setCode(500);
            apiResponse.setMessage("插入插入orderDetail失败！");
            apiResponse.setData(orderDetail);
            return apiResponse;
        }
    }

    @Override
    public ApiResponse getOrderDetailByOrderId(String orderId) {
        ApiResponse<List> listApiResponse = new ApiResponse<>();
        if(StringUtils.isBlank(orderId)){
            listApiResponse.setMessage("orderId为空！");
            listApiResponse.setCode(400);
            return listApiResponse;
        }
        try {
            List<OrderDetail> orderDetails = orderDetailMapper.selectOrderDetailByOrderId(orderId);
            listApiResponse.setCode(200);
            listApiResponse.setMessage("获取订单详情成功！");
            listApiResponse.setData(orderDetails);
            if(orderDetails==null||orderDetails.size()==0){
                listApiResponse.setCode(200);
                listApiResponse.setMessage("该订单号详情为空！！");

            }
            return listApiResponse;
        }catch (Exception e){
            e.printStackTrace();
            listApiResponse.setMessage("获取订单详情失败！");
            listApiResponse.setCode(500);
            return listApiResponse;
        }

    }
}
