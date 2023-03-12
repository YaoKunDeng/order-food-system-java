package com.dgut.orderfoodsystem.mapper;

import com.dgut.orderfoodsystem.entity.Dishes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-11
 */
@Mapper
@Repository
public interface DishesMapper {
    void addDish(Dishes dishes);

    void delDish(String id);

    void updateDish(Dishes dishes);

    List<Dishes> selectDishesByStoreId(String StoreId);

    Dishes selectDishById(String id);
}
