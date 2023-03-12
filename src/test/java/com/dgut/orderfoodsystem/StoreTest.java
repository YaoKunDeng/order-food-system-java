package com.dgut.orderfoodsystem;

import com.dgut.orderfoodsystem.entity.Store;
import com.dgut.orderfoodsystem.service.StoreService;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-9
 */
@SpringBootTest
public class StoreTest {
    @Resource
    private StoreService storeService;

    @Test
    public void testAddStore(){
        Store store = new Store();
        store.setAddress("广东省茂名市电白区旦场镇长坑村");
        store.setStoreName("小香港店");
        store.setUserId("411e2091-8c05-4280-9e7d-02440c05370f");
        ApiResponse response = storeService.addStore(store);
        System.out.println(response);
    }

    @Test
    public void testGetStore(){
        ApiResponse store = storeService.getStoreByName("小香港店");
        System.out.println(store);
    }

}
