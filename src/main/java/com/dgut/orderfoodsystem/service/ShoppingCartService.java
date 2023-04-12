package com.dgut.orderfoodsystem.service;

import com.dgut.orderfoodsystem.entity.ShoppingCart;
import com.dgut.orderfoodsystem.utils.ApiResponse;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-19
 */
public interface ShoppingCartService {
    ApiResponse addShoppingCart(ShoppingCart shoppingCart);

    ApiResponse getShoppingCartByShoppingNumber(String ShoppingNumber);
}
