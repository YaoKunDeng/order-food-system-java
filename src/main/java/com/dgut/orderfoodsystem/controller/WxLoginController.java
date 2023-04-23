package com.dgut.orderfoodsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.dgut.orderfoodsystem.entity.LoginTicket;
import com.dgut.orderfoodsystem.entity.WxUser;
import com.dgut.orderfoodsystem.service.LoginTicketService;
import com.dgut.orderfoodsystem.service.WxUserService;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import com.dgut.orderfoodsystem.utils.WxUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-2-28
 */
@RestController
@RequestMapping("/wx")
public class WxLoginController {

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.appSecret}")
    private String appSecret;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private WxUtils wxUtils;

    @Resource
    private WxUserService wxUserService;

    @Resource
    private LoginTicketService loginTicketService;

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public ApiResponse wxLogin(String code, String iv, String encryptedData) throws UnsupportedEncodingException {
        ApiResponse<Map> mapApiResponse = new ApiResponse<>();
        Map<String, Object> resultMap = new HashMap<>();
        JSONObject sessionKeyAndOpenid = wxUtils.getSessionKeyAndOpenid(code);
        String openId = (String) sessionKeyAndOpenid.get("openid");
        String session_key = (String) sessionKeyAndOpenid.get("session_key");

//        URLEncoder.encode(iv,"UTF-8").replace("%3D","=").replace("%2F","/");
//        URLEncoder.encode(encryptedData,"UTF-8").replace("%3D","=").replace("%2F","/");
        WxUser userInfo = wxUtils.getUserInfo(encryptedData, session_key, iv);
        userInfo.setOpenId(openId);
        userInfo.setCreateTime(new Date());
        //查询数据库中是否有登录信息
        WxUser wxUser = wxUserService.selectUserByOpenId(openId);
        //如果数据库中没有，那么插入数据
        if(wxUser==null){
            ApiResponse login = wxUserService.register(userInfo);
            //判断插入是否成功
            if(login.getCode()!=200)
                return login;
        }
        //将session_key存到数据库登录信息表
        LoginTicket loginTicketByTicket = loginTicketService.getLoginTicketByTicket(session_key);
        //微信的session有时候是相同的，比如短时间内
        if(loginTicketByTicket==null)
            loginTicketService.addLoginTicket(openId,session_key,10,0);
        //将用户信息和session_key返回给前端
        resultMap.put("userInfo", userInfo);
        resultMap.put("token",session_key);
        mapApiResponse.setCode(200);
        mapApiResponse.setMessage("登录成功!");
        mapApiResponse.setData(resultMap);
        return mapApiResponse;
    }

    @RequestMapping(path = "users",method = RequestMethod.GET)
    public ApiResponse getWxUsers(){
        ApiResponse wxUsers = wxUserService.getWxUsers();
        return wxUsers;
    }

    @RequestMapping(path = "delUser", method = RequestMethod.GET)
    public ApiResponse delWxUser(String openId){
        System.out.println(openId);
        ApiResponse response = wxUserService.delWxUser(openId);
        return response;
    }
}
