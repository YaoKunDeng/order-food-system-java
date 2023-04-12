package com.dgut.orderfoodsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-3-3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxUser {
    private String openId;
    private String nickname;
    private String avatarUrl;
    private Integer gender;
    private String province;
    private String city;
    private String country;
    private String language;
    private Date createTime;
    private Date updateTime;
}
