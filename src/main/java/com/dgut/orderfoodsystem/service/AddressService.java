package com.dgut.orderfoodsystem.service;

import com.dgut.orderfoodsystem.entity.Address;
import com.dgut.orderfoodsystem.utils.ApiResponse;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-4-11
 */
public interface AddressService {
     ApiResponse addAddress(Address address);

     ApiResponse getAddresses(String userId);

     ApiResponse updateAddress(Address address);

     ApiResponse delAddress(String addressId);


}
