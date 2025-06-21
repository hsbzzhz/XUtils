package com.haisum.redissondemo.service.impl;

import com.haisum.redissondemo.common.tcc.TccContext;
import com.haisum.redissondemo.common.tcc.TccParticipant;

public class OrderServiceImpl implements TccParticipant {

    private String orderId;

    public OrderServiceImpl(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public void tryExecute(TccContext context) throws Exception {
        System.out.println("订单创建");
        // 插入订单创建业务，状态为“预创建”
    }

    @Override
    public void confirm(TccContext context) {
        if (context.isCommitted()) {
            System.out.println("订单提交");
            // 更新订单状态为“已提交”
        }
    }

    @Override
    public void cancel(TccContext context) {
        System.out.println("删除订单");
        // 回滚业务
    }
}
