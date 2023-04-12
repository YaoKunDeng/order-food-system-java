package com.dgut.orderfoodsystem.mapper;

import com.dgut.orderfoodsystem.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-4-11
 */
@Mapper
@Repository
public interface AddressMapper {
    void insertAddress(Address address);

    List<Address> getAddressByUserId(String UserId);

    void updateAddress(Address address);

    void deleteAddressById(String addressId);
}
