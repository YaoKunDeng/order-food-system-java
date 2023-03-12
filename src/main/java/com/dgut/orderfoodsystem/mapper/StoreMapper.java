package com.dgut.orderfoodsystem.mapper;

import com.dgut.orderfoodsystem.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-9
 */
@Mapper
@Repository
public interface StoreMapper {
    void insertStore(Store store);

    Store selectStoreByStoreName(String name);

    Store selectStoreByUserId(String id);

    Store selectStoreById(String id);
}
