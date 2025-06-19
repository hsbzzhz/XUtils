package com.haisum.redissondemo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Hongzhi Zhang
 * @Date: 2025/6/19
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderTask implements Serializable {
    private String orderId;
    private String productName;
    private long createTime;
    private long expireDelay;

}
