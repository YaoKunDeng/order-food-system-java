package com.dgut.orderfoodsystem.mapper;

import com.dgut.orderfoodsystem.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-9
 */
@Repository
@Mapper
public interface MenuMapper {
   void insertMenu(Menu menu);

   List<Menu> selectMenusByStoreId(String storeId);

   void updateMenu(Menu menu);

   void deleteMenu(String id);

   Menu selectMenuById(String id);
}
