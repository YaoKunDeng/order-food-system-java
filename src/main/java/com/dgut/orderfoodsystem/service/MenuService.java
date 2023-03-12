package com.dgut.orderfoodsystem.service;

import com.dgut.orderfoodsystem.entity.Menu;
import com.dgut.orderfoodsystem.utils.ApiResponse;

import java.util.List;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-9
 */
public interface MenuService {
    ApiResponse addMenu(Menu menu);

    ApiResponse deleteMenu(String id);

    List getMenusByStoreId(String storeId);

    ApiResponse updateMenu(Menu menu);

    ApiResponse<Menu> getMenuById(String id);
}
