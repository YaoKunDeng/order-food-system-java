package com.dgut.orderfoodsystem.service.impl;
import com.dgut.orderfoodsystem.entity.User;
import com.dgut.orderfoodsystem.mapper.UserMapper;
import com.dgut.orderfoodsystem.service.UserService;
import com.dgut.orderfoodsystem.utils.ApiResponse;
import com.dgut.orderfoodsystem.utils.Md5Thicken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;
/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-3
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public ApiResponse register(User user) {
        ApiResponse<User> response = new ApiResponse<>();
        if(user==null){
            response.setCode(500);
            response.setMessage("用户信息不能为空!");
            return response;
        }
        if(StringUtils.isBlank(user.getPassword())||StringUtils.isBlank(user.getUsername())){
            response.setCode(500);
            response.setMessage("账号或密码不能为空！");
            return response;
        }
        //判断数据库中是否存在这个用户名
        User user1 = userMapper.selectUserByUsername(user.getUsername());
        if(user1!=null){
            response.setCode(500);
            response.setMessage("该用户名已经存在！");
            return response;
        }
        //补充信息
        user.setSalt(UUID.randomUUID().toString().replaceAll("-","").substring(0,5));
        user.setPassword(Md5Thicken.MD5(user.getPassword()+user.getSalt()));
        user.setCreateTime(new Date());
        user.setId(UUID.randomUUID().toString());
        user.setUpdateTime(new Date());
        System.out.println(user);
        userMapper.insertUser(user);
        response.setCode(200);
        response.setMessage("登录成功");
        //写到这里啦
        return response;
    }

    @Override
    public ApiResponse login(String username, String password) {
        ApiResponse<User> response = new ApiResponse<>();
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            response.setCode(500);
            response.setMessage("账号或密码不能为空");
            return response;
        }
        User user1 = userMapper.selectUserByUsername(username);
        if(user1==null){
            response.setCode(500);
            response.setMessage("该用户不存在，请联系管理员注册账号!");
            return response;
        }
        boolean equals = user1.getPassword().equals(Md5Thicken.MD5(password + user1.getSalt()));
        if(!equals){
            response.setCode(500);
            response.setMessage("密码不正确");
            return response;
        }
        response.setData(user1);
        response.setCode(200);
        response.setMessage("登录成功");
        return response;
    }
}
