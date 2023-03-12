package com.dgut.orderfoodsystem.mapper;

import com.dgut.orderfoodsystem.entity.WxUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-3
 */
@Mapper
@Repository
public interface WxUserMapper {
    void addWxUser(WxUser wxUser);

    WxUser selectWxUserByOpenId(String openId);

}
