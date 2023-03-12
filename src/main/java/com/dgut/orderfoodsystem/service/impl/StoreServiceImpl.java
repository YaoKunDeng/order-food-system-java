package com.dgut.orderfoodsystem.service.impl;

import com.dgut.orderfoodsystem.entity.Store;
import com.dgut.orderfoodsystem.mapper.StoreMapper;
import com.dgut.orderfoodsystem.service.StoreService;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import com.dgut.orderfoodsystem.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-9
 */
@Service
public class StoreServiceImpl implements StoreService {
    @Resource
    private StoreMapper storeMapper;

    @Override
    public ApiResponse addStore(Store store) {
        ApiResponse<Store> storeApiResponse = new ApiResponse<>();
        if(store==null){
            storeApiResponse.setCode(400);
            storeApiResponse.setMessage("店铺信息不能为空！");
            return storeApiResponse;
        }
        if(StringUtils.isBlank(store.getStoreName())){
            storeApiResponse.setCode(400);
            storeApiResponse.setMessage("店铺名称不能为空!");
            return storeApiResponse;
        }
        if(StringUtils.isBlank(store.getAddress())){
            storeApiResponse.setCode(400);
            storeApiResponse.setMessage("店铺地址不能为空");
            return storeApiResponse;
        }
        //构造店铺信息
        store.setId(CommonUtils.generateId());
        store.setCreateTime(new Date());
        try{
            storeMapper.insertStore(store);
        }catch (Exception e){
            storeApiResponse.setCode(500);
            storeApiResponse.setMessage("写入数据库失败！");
            return storeApiResponse;
        }
        storeApiResponse.setData(store);
        storeApiResponse.setCode(200);
        storeApiResponse.setMessage("添加成功！");
        return storeApiResponse;
    }

    @Override
    public ApiResponse getStoreByName(String storeName) {
        ApiResponse<Store> storeApiResponse = new ApiResponse<>();
        if(StringUtils.isBlank(storeName)){
            storeApiResponse.setMessage("店铺名为空！");
            storeApiResponse.setCode(400);
            return storeApiResponse;
        }
        Store store = storeMapper.selectStoreByStoreName(storeName);
        if(store==null){
            storeApiResponse.setCode(400);
            storeApiResponse.setMessage("该店铺不存在!");
            return storeApiResponse;
        }
        storeApiResponse.setData(store);
        storeApiResponse.setCode(200);
        storeApiResponse.setMessage("查询成功");
        return storeApiResponse;
    }

    @Override
    public ApiResponse getStoreByUserId(String id) {
        ApiResponse<Store> stringApiResponse = new ApiResponse<>();
        if(StringUtils.isBlank(id)){
            stringApiResponse.setCode(400);
            stringApiResponse.setMessage("获取店铺信息失败！");
            return stringApiResponse;
        }
        try {
            Store store = storeMapper.selectStoreByUserId(id);
            if(store!=null){
                stringApiResponse.setCode(200);
                stringApiResponse.setMessage("获取店铺信息成功!");
                stringApiResponse.setData(store);
            }else{
                stringApiResponse.setCode(400);
                stringApiResponse.setMessage("该用户不存在店铺信息，请联系管理员！");
            }
        }catch (Exception e){
            stringApiResponse.setCode(500);
            stringApiResponse.setMessage("获取店铺信息失败!");
            return stringApiResponse;
        }
        return stringApiResponse;
    }

    @Override
    public ApiResponse getStoreById(String id) {
        ApiResponse<Store> storeApiResponse = new ApiResponse<>();
        if(StringUtils.isBlank(id)){
            storeApiResponse.setCode(400);
            storeApiResponse.setMessage("查询参数id为空！");
            return storeApiResponse;
        }
        try {
            Store store = storeMapper.selectStoreById(id);
            if(store==null){
                storeApiResponse.setCode(400);
                storeApiResponse.setMessage("查询不到指定店铺信息！");
                return storeApiResponse;
            }
            storeApiResponse.setMessage("查询店铺信息成功！");
            storeApiResponse.setCode(200);
            storeApiResponse.setData(store);
            return storeApiResponse;
        }catch (Exception e){
            storeApiResponse.setCode(500);
            storeApiResponse.setMessage("查询店铺信息错误！");
            return storeApiResponse;
        }
    }
}
