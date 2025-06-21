package com.haisum.redissondemo.common.tcc;

public class TccContext {
    private final String xid; // 全局事务id
    private boolean committed; // 事务提交状态

    public TccContext(String xid) {
        this.xid = xid;
    }

    public synchronized void commit() {
        this.committed = true;
    }

    public boolean isCommitted() {
        return committed;
    }
}
