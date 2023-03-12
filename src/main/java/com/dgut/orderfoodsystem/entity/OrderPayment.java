package com.dgut.orderfoodsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-2-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPayment {
    private String id;
    private String orderId;
    private BigDecimal amount;
    private Date payTime;
    private String status; //支付状态
    private String paymentWay; //支付方式
}
