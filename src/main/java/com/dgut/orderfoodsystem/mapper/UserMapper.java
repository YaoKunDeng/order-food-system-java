package com.dgut.orderfoodsystem.mapper;

import com.dgut.orderfoodsystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;


/**
 * @Description TODO
 * @Author akun
 * @Data 2023-2-25
 */
@Mapper
@Repository
public interface UserMapper {

    void insertUser(User user);

    User selectUserByUsername(String username);

    void updateLoginTime(@Param("date") Date date, @Param("username") String username);
}
