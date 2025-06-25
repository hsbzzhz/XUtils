package com.haisum.dbdemo.repository.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Description:
 * @Author: Hongzhi Zhang
 * @Date: 2025/6/25
 */

@Entity
public class paymentTransaction {
    @Id
    private String txId;  // 全局事务ID
    private String actionId; // 分布式ID
    private String businessId; // 业务ID（订单id）
    private Integer status; // 状态： 1-TRY， 2-CONFIRM，3-CANCEL
    private Long createTime;
}
