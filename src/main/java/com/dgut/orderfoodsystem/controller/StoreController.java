package com.dgut.orderfoodsystem.controller;

import com.dgut.orderfoodsystem.service.StoreService;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-9
 */
@RestController
@RequestMapping("/store")
public class StoreController {
    @Resource
    private StoreService storeService;

    @RequestMapping(path = "/get",method = RequestMethod.GET)
    public ApiResponse getName(String id){
        System.out.println(id);
        ApiResponse storeNameById = storeService.getStoreByUserId(id);
        System.out.println(storeNameById);
        return storeNameById;
    }
}
