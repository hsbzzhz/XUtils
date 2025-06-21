package com.haisum.redissondemo.service.impl;

import com.haisum.redissondemo.common.tcc.TccContext;
import com.haisum.redissondemo.common.tcc.TccParticipant;

public class InventoryServiceImpl implements TccParticipant {

    private String productId;
    private int quantity;

    public InventoryServiceImpl(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public void tryExecute(TccContext context) throws Exception {
        System.out.println("库存冻结");
        // 实际业务：预扣除库存quantity数量
    }

    @Override
    public void confirm(TccContext context) {
        System.out.println("库存扣减");
        // 实际业务：库存扣减quantity数量
    }

    @Override
    public void cancel(TccContext context) {
        System.out.println("库存释放");
        // 实际业务：释放预扣除quantity数量
    }
}
