package com.dgut.orderfoodsystem.service.impl;

import com.dgut.orderfoodsystem.entity.Address;
import com.dgut.orderfoodsystem.mapper.AddressMapper;
import com.dgut.orderfoodsystem.service.AddressService;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import com.dgut.orderfoodsystem.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-4-11
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressMapper addressMapper;
    @Override
    public ApiResponse addAddress(Address address) {
        ApiResponse<Address> response = new ApiResponse<>();
        System.out.println(address);
        if(address==null){
            response.setCode(400);
            response.setMessage("address为空!");
            return response;
        }
        if(StringUtils.isBlank(address.getHouseNum())){
            response.setCode(400);
            response.setMessage("门牌号为空!");
            response.setData(address);
            return response;
        }
        if(StringUtils.isBlank(address.getUserId())){
            response.setCode(400);
            response.setMessage("用户id为空为空!");
            response.setData(address);
            return response;
        }
        if(StringUtils.isBlank(address.getUserName())){
            response.setCode(400);
            response.setMessage("联系人为空!");
            response.setData(address);
            return response;
        }
        try {
            address.setId(CommonUtils.generateId());
            address.setCreateTime(new Date());
            addressMapper.insertAddress(address);
            response.setData(address);
            response.setCode(200);
            response.setMessage("插入address成功！");
            return response;
        }catch (Exception e) {
            e.printStackTrace();
            response.setData(address);
            response.setCode(500);
            response.setMessage("插入address失败！");
            return response;
        }
    }

    @Override
    public ApiResponse getAddresses(String userId) {
        ApiResponse<List> listApiResponse = new ApiResponse<>();
        if(StringUtils.isBlank(userId)){
            listApiResponse.setCode(400);
            listApiResponse.setMessage("userId为空！");
            return listApiResponse;
        }
        try {
            List<Address> addressByUserId = addressMapper.getAddressByUserId(userId);
            listApiResponse.setMessage("获取地址列表成功！");
            listApiResponse.setCode(200);
            listApiResponse.setData(addressByUserId);
            if (addressByUserId==null||addressByUserId.size()==0){
                listApiResponse.setMessage("地址列表为空！");
                listApiResponse.setCode(400);
            }
            return listApiResponse;
        }catch (Exception e){
            e.printStackTrace();
            listApiResponse.setCode(500);
            listApiResponse.setMessage("获取地址列表失败！");
            return listApiResponse;
        }

    }

    @Override
    public ApiResponse updateAddress(Address address) {
        ApiResponse<Address> response = new ApiResponse<>();
        if(address==null){
            response.setMessage("address为空！");
            response.setCode(400);
            return response;
        }
        if(StringUtils.isBlank(address.getHouseNum())){
            response.setCode(400);
            response.setMessage("门牌号为空!");
            response.setData(address);
            return response;
        }
        if(StringUtils.isBlank(address.getUserId())){
            response.setCode(400);
            response.setMessage("用户id为空为空!");
            response.setData(address);
            return response;
        }
        if(StringUtils.isBlank(address.getUserName())){
            response.setCode(400);
            response.setMessage("联系人为空!");
            response.setData(address);
            return response;
        }
        try {
            addressMapper.updateAddress(address);
            response.setData(address);
            response.setCode(200);
            response.setMessage("更新address成功！");
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(500);
            response.setMessage("更新地址失败！");
            return response;
        }

    }

    @Override
    public ApiResponse delAddress(String addressId) {
        ApiResponse<Address> response = new ApiResponse<>();
        if(StringUtils.isBlank(addressId)){
            response.setCode(400);
            response.setMessage("addressId为空！");
            return response;
        }
        try {
            addressMapper.deleteAddressById(addressId);
            response.setCode(200);
            response.setMessage("删除address成功！");
            return response;
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(500);
            response.setMessage("删除address失败！");
            return response;
        }
    }
}
