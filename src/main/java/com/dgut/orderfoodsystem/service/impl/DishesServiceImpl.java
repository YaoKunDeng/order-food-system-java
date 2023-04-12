package com.dgut.orderfoodsystem.service.impl;

import com.dgut.orderfoodsystem.entity.Dishes;
import com.dgut.orderfoodsystem.mapper.DishesMapper;
import com.dgut.orderfoodsystem.service.DishesService;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import com.dgut.orderfoodsystem.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-11
 */

@Service
public class DishesServiceImpl implements DishesService {
    @Resource
    private DishesMapper dishesMapper;
    @Override
    public ApiResponse addDish(Dishes dishes) {
        ApiResponse<Dishes> dishesApiResponse = new ApiResponse<>();
        //调用私有函数，进行dishes数据校验
        ApiResponse validate = validate(dishes);
        if(validate.getCode()!=200)
            return validate;
        //构造数据
        dishes.setId(CommonUtils.generateId());
        dishes.setCreateTime(new Date());
        try{
            dishesMapper.addDish(dishes);
            dishesApiResponse.setCode(200);
            dishesApiResponse.setMessage("添加商品成功");
            dishesApiResponse.setData(dishes);
            return dishesApiResponse;
        }catch (Exception e){
            dishesApiResponse.setCode(500);
            dishesApiResponse.setMessage("添加商品失败");
            e.printStackTrace();
            return dishesApiResponse;
        }

    }

    @Override
    public ApiResponse delDish(String id) {
        ApiResponse<Dishes> dishesApiResponse = new ApiResponse<>();
        if (StringUtils.isBlank(id)){
            dishesApiResponse.setCode(400);
            dishesApiResponse.setMessage("商品id不能为空");
            return dishesApiResponse;
        }
        try {
            dishesMapper.delDish(id);
            dishesApiResponse.setCode(200);
            dishesApiResponse.setMessage("删除商品成功！");
            return dishesApiResponse;
        }catch (Exception e){
            dishesApiResponse.setCode(500);
            dishesApiResponse.setMessage("删除商品失败！");
            return dishesApiResponse;
        }

    }

    @Override
    public ApiResponse updateDish(Dishes dishes) {
        ApiResponse<Dishes> dishesApiResponse = new ApiResponse<>();
        ApiResponse validate = validate(dishes);
        if(validate.getCode()!=200)
            return validate;
        try{
            dishesMapper.updateDish(dishes);
            dishesApiResponse.setCode(200);
            dishesApiResponse.setMessage("更新商品成功");
            dishesApiResponse.setData(dishes);
            return dishesApiResponse;
        }catch (Exception e){
            dishesApiResponse.setCode(500);
            dishesApiResponse.setMessage("更新商品失败");
            return dishesApiResponse;
        }

    }

    @Override
    public ApiResponse getDishesByStoreId(String storeId) {
        ApiResponse<List> listApiResponse = new ApiResponse<>();
        if(StringUtils.isBlank(storeId)){
            listApiResponse.setCode(400);
            listApiResponse.setMessage("店铺id为空，获取店铺商品失败！");
            return listApiResponse;
        }
        try {
            List<Dishes> dishes = dishesMapper.selectDishesByStoreId(storeId);
            if(dishes.size()==0){
                listApiResponse.setCode(201);
                listApiResponse.setMessage("该店铺暂时没有商品！");
                return listApiResponse;
            }
            listApiResponse.setCode(200);
            listApiResponse.setMessage("获取商品信息成功！");
            listApiResponse.setData(dishes);
            return listApiResponse;
        }catch (Exception e){
            e.printStackTrace();
            listApiResponse.setCode(500);
            listApiResponse.setMessage("获取商品信息失败！");
            return listApiResponse;
        }
    }

    @Override
    public ApiResponse getDishById(String id) {
        ApiResponse<Dishes> dishesApiResponse = new ApiResponse<>();
        if(StringUtils.isBlank(id)){
            dishesApiResponse.setCode(400);
            dishesApiResponse.setMessage("菜品id为空！");
            return dishesApiResponse;
        }
        try{
            Dishes dishes = dishesMapper.selectDishById(id);
            if(dishes==null){
                dishesApiResponse.setCode(400);
                dishesApiResponse.setMessage("菜品为空！");
                return dishesApiResponse;
            }
            dishesApiResponse.setCode(200);
            dishesApiResponse.setMessage("获取菜品成功");
            dishesApiResponse.setData(dishes);
            return dishesApiResponse;
        }catch (Exception e){
            dishesApiResponse.setCode(500);
            dishesApiResponse.setMessage("获取菜品失败！！");
            return dishesApiResponse;
        }

    }

    private ApiResponse validate(Dishes dishes){
        ApiResponse<Dishes> dishesApiResponse = new ApiResponse<>();
        if(dishes==null){
            dishesApiResponse.setCode(400);
            dishesApiResponse.setMessage("菜品信息不能为空！");
            return dishesApiResponse;
        }
        if(StringUtils.isBlank(dishes.getDishName())){
            dishesApiResponse.setCode(400);
            dishesApiResponse.setMessage("菜品名称不能为空！！");
            return dishesApiResponse;
        }
        if(StringUtils.isBlank(dishes.getDescription())){
            dishesApiResponse.setCode(400);
            dishesApiResponse.setMessage("菜品描述不能为空！！");
            return dishesApiResponse;
        }
        if(StringUtils.isBlank(dishes.getMenuId())){
            dishesApiResponse.setCode(400);
            dishesApiResponse.setMessage("菜品类别不能为空！！");
            return dishesApiResponse;
        }
        if(StringUtils.isBlank(dishes.getStoreId())){
            dishesApiResponse.setCode(400);
            dishesApiResponse.setMessage("店铺id不能为空！！");
            return dishesApiResponse;
        }

        if(dishes.getNewPrice().equals(BigDecimal.valueOf(0))){
            dishesApiResponse.setCode(500);
            dishesApiResponse.setMessage("价格不能小于0！！");
            return dishesApiResponse;
        }
        if(dishes.getOldPrice().equals(BigDecimal.valueOf(0))){
            dishesApiResponse.setCode(500);
            dishesApiResponse.setMessage("价格不能小于0！！");
            return dishesApiResponse;
        }
        dishesApiResponse.setCode(200);
        return dishesApiResponse;
    }
}
