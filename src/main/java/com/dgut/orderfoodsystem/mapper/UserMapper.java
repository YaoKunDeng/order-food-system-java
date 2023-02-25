package com.dgut.orderfoodsystem.mapper;

import com.dgut.orderfoodsystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-2-25
 */
@Mapper
@Repository
public interface UserMapper {

    void insertUser(User user);

    List<User> selectAllUser();
}
