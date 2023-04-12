package com.dgut.orderfoodsystem.service.impl;

import com.dgut.orderfoodsystem.entity.ShoppingCart;
import com.dgut.orderfoodsystem.mapper.ShoppingCartMapper;
import com.dgut.orderfoodsystem.service.ShoppingCartService;
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
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Resource
    private ShoppingCartMapper shoppingCartMapper;
    @Override
    public ApiResponse addShoppingCart(ShoppingCart shoppingCart) {
        ApiResponse<ShoppingCart> apiResponse = new ApiResponse<>();
        if(shoppingCart==null){
            apiResponse.setCode(400);
            apiResponse.setMessage("shoppingCart为空！");
            return apiResponse;
        }

        shoppingCart.setId(CommonUtils.generateId());
        shoppingCart.setCreateTime(new Date());
        try {
            shoppingCartMapper.insertShoppingCart(shoppingCart);
            apiResponse.setCode(200);
            apiResponse.setMessage("插入ShoppingCart成功！");
            apiResponse.setData(shoppingCart);
            return apiResponse;
        }catch (Exception e){
            e.printStackTrace();
            apiResponse.setCode(200);
            apiResponse.setMessage("插入ShoppingCart失败！");
            apiResponse.setData(shoppingCart);
            return apiResponse;
        }


    }

    @Override
    public ApiResponse getShoppingCartByShoppingNumber(String shoppingNumber) {
        ApiResponse<List> listApiResponse = new ApiResponse<>();
        if(StringUtils.isBlank(shoppingNumber)){
            listApiResponse.setMessage("shoppingNumber为空！");
            listApiResponse.setCode(400);
            return listApiResponse;
        }
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectShoppingCartsByShoppingNumber(shoppingNumber);
        listApiResponse.setCode(200);
        listApiResponse.setMessage("获取购物车列表成功！");
        listApiResponse.setData(shoppingCarts);
        if(shoppingCarts==null || shoppingCarts.size()==0){
            listApiResponse.setCode(400);
            listApiResponse.setMessage("购物车列表为空");
        }
        return listApiResponse;
    }
}
