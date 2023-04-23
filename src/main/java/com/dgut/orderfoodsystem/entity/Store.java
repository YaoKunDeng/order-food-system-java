package com.dgut.orderfoodsystem.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
/**
 * @Description TODO
 * @Author akun
 * @Data 2023-2-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    private String id;
    private String userId;
    private String storeName;
    private String address;
    private String latitude;
    private String longitude;
    private Date createTime;
}
