package com.dgut.orderfoodsystem.service;

import com.dgut.orderfoodsystem.entity.Store;
import com.dgut.orderfoodsystem.utils.ApiResponse;
/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-9
 */
public interface StoreService {
    ApiResponse addStore(Store store);

    ApiResponse getStoreByName(String storeName);

    ApiResponse getStoreByUserId(String id);

    ApiResponse getStoreById(String id);

    ApiResponse updateStore(Store store);
}
