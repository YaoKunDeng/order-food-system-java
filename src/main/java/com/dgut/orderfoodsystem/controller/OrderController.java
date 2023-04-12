package com.dgut.orderfoodsystem.controller;

import cn.hutool.db.sql.Order;
import com.alibaba.fastjson2.JSON;
import com.dgut.orderfoodsystem.entity.Orders;
import com.dgut.orderfoodsystem.entity.OrderDetail;
import com.dgut.orderfoodsystem.entity.ShoppingCart;
import com.dgut.orderfoodsystem.service.OrderDetailService;
import com.dgut.orderfoodsystem.service.OrderService;
import com.dgut.orderfoodsystem.service.ShoppingCartService;
import com.dgut.orderfoodsystem.service.StoreService;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import com.dgut.orderfoodsystem.utils.CommonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-23
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private ShoppingCartService shoppingCartService;
    @Resource
    private OrderService orderService;

    @Resource
    private OrderDetailService orderDetailService;

    @Resource
    private StoreService storeService;


    @RequestMapping(path = "/add/shopping",method = RequestMethod.POST)
    public ApiResponse addShopping(String jsonShopping){
        ApiResponse<String> stringApiResponse = new ApiResponse<>();
        List<ShoppingCart> shoppingCarts = JSON.parseArray(jsonShopping, ShoppingCart.class);
        String shoppingNumber = CommonUtils.generateId();
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCart.setShoppingNumber(shoppingNumber);
            ApiResponse response = shoppingCartService.addShoppingCart(shoppingCart);
            if(response.getCode()!=200)
                return response;
        }
        stringApiResponse.setMessage("添加购物车成功！");
        stringApiResponse.setCode(200);
        stringApiResponse.setData(shoppingNumber);
        return stringApiResponse;
    };
    @RequestMapping(path = "/get/shopping",method = RequestMethod.GET)
    public ApiResponse getShopping(String shoppingNumber){
        ApiResponse shoppingCartByShoppingNumber = shoppingCartService.getShoppingCartByShoppingNumber(shoppingNumber);
        return shoppingCartByShoppingNumber;
    }

    //添加订单
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ApiResponse<Orders> addOrder(Orders orders){
        ApiResponse response = orderService.addOrder(orders);
        return response;
    }

    //获取订单
    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public ApiResponse getOrderByUserId(String userId){
        List<Map> resultList = new ArrayList<>();
        ApiResponse<List> response = new ApiResponse<>();
        ApiResponse orderByUserId = orderService.getOrderByUserId(userId);
        List<Orders> orders = (List<Orders>) orderByUserId.getData();
        for (Orders order : orders) {
            HashMap<String, Object> map = new HashMap<>();
            ApiResponse orderDetailByOrderId = orderDetailService.getOrderDetailByOrderId(order.getId());
            map.put("order",order);

            //获取店铺信息
            ApiResponse store = storeService.getStoreById(order.getStoreId());
            map.put("store",store.getData());
            if(orderDetailByOrderId.getCode()==200){
                List<OrderDetail> orderDetails = (List<OrderDetail>) orderDetailByOrderId.getData();
                map.put("orderDetail",orderDetails);
            }
            resultList.add(map);
        }
        response.setData(resultList);
        response.setMessage("获取订单成功！");
        response.setCode(200);

        return response;
    }
    //添加订单详情
    @RequestMapping(path = "/add/details", method = RequestMethod.POST)
    public ApiResponse addOrderDetail(String jsonOrderDetail){
        ApiResponse<List> listApiResponse = new ApiResponse<>();
        List<OrderDetail> orderDetails = JSON.parseArray(jsonOrderDetail, OrderDetail.class);
        for (OrderDetail orderDetail : orderDetails) {
            ApiResponse response = orderDetailService.addOrderDetail(orderDetail);
            if (response.getCode()!=200){
                return response;
            }
        }
        listApiResponse.setCode(200);
        listApiResponse.setMessage("添加订单详情成功!");
        listApiResponse.setData(orderDetails);
        return listApiResponse;
    }
    //获取订单详情byOrderId
    @RequestMapping(path = "/get/details", method = RequestMethod.GET)
    public ApiResponse getOrderDetailByOrderId(String orderId){
        ApiResponse orderDetailByOrderId = orderDetailService.getOrderDetailByOrderId(orderId);
        return orderDetailByOrderId;
    }

}
