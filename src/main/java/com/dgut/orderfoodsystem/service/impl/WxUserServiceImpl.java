package com.dgut.orderfoodsystem.service.impl;

import com.dgut.orderfoodsystem.entity.WxUser;
import com.dgut.orderfoodsystem.mapper.WxUserMapper;
import com.dgut.orderfoodsystem.service.WxUserService;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-3
 */
@Service
public class WxUserServiceImpl implements WxUserService {
    @Resource
    private WxUserMapper wxUserMapper;
    @Override
    public ApiResponse register(WxUser wxUser) {
        ApiResponse<WxUser> apiResponse = new ApiResponse<>();
        if(wxUser==null){
            apiResponse.setCode(500);
            apiResponse.setMessage("获取用户信息失败！");
            return apiResponse;
        }
        if( StringUtils.isBlank(wxUser.getOpenId())){
            apiResponse.setCode(500);
            apiResponse.setMessage("获取用户id失败");
            return apiResponse;
        }
        wxUser.setCreateTime(new Date());
        wxUserMapper.addWxUser(wxUser);
        apiResponse.setCode(200);
        apiResponse.setMessage("登录成功");
        return apiResponse;
    }

    @Override
    public WxUser selectUserByOpenId(String openId) {
        WxUser wxUserByOpenId = wxUserMapper.selectWxUserByOpenId(openId);
        return wxUserByOpenId;
    }
}
