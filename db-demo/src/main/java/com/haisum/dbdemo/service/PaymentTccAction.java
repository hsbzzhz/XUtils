package com.haisum.dbdemo.service;


import java.math.BigDecimal;

/**
 * @Description:
 * @Author: Hongzhi Zhang
 * @Date: 2025/6/25
 */

public interface PaymentTccAction {
    boolean tryPayment(String orderId, BigDecimal amount);
    boolean confirm(String context);

}
