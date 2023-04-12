package com.dgut.orderfoodsystem.mapper;

import com.dgut.orderfoodsystem.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-25
 */
@Repository
@Mapper
public interface OrderMapper {
    void insertOrder(Orders orders);

    List<Orders> selectOrdersByUserId(String userId);
}
