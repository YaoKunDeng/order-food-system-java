package com.dgut.orderfoodsystem.service;

import com.dgut.orderfoodsystem.entity.Dishes;
import com.dgut.orderfoodsystem.utils.ApiResponse;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-11
 */
public interface DishesService {
    ApiResponse addDish(Dishes dishes);

    ApiResponse delDish(String id);

    ApiResponse updateDish(Dishes dishes);

    ApiResponse getDishesByStoreId(String storeId);

    ApiResponse getDishById(String id);
}
