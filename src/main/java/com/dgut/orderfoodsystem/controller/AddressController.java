package com.dgut.orderfoodsystem.controller;

import com.dgut.orderfoodsystem.entity.Address;
import com.dgut.orderfoodsystem.service.AddressService;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-4-11
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    @Resource
    private AddressService addressService;

    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public ApiResponse addAddress(Address address){
        ApiResponse response = addressService.addAddress((address));
        return response;
    }
    @RequestMapping(path = "/get/userId",method = RequestMethod.GET)
    public ApiResponse getAddressesByUserId(String userId){
        ApiResponse addresses = addressService.getAddresses(userId);
        return addresses;
    }
    @RequestMapping(path = "/update",method = RequestMethod.POST)
    public ApiResponse updateAddress(Address address){
        ApiResponse response = addressService.updateAddress(address);
        return response;
    }

    @RequestMapping(path = "/del",method = RequestMethod.GET)
    public ApiResponse delAddress(String addressId){
        ApiResponse response = addressService.delAddress(addressId);
        return response;
    }
}
