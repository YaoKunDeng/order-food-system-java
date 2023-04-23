package com.dgut.orderfoodsystem.controller;

import com.dgut.orderfoodsystem.entity.Store;
import com.dgut.orderfoodsystem.service.StoreService;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import com.dgut.orderfoodsystem.utils.Geocoding;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

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
    public ApiResponse getStoreByUserId(String id){
        System.out.println(id);
        ApiResponse storeNameById = storeService.getStoreByUserId(id);
        System.out.println(storeNameById);
        return storeNameById;
    }

    @RequestMapping(path = "update",method = RequestMethod.POST)
    public ApiResponse updateStore(Store store) throws IOException {
        if(!StringUtils.isBlank(store.getAddress())){
            ApiResponse response = Geocoding.addressExchange(store.getAddress());
            if(response.getCode()!=200){
                return response;
            }
            Map data = (Map) response.getData();
            store.setLongitude((String) data.get("longitude"));
            store.setLatitude((String) data.get("latitude"));
        }
        ApiResponse response = storeService.updateStore(store);
        return response;
    }

}
