package com.haisum.redissondemo.common.tcc;

public interface TccParticipant {

    void tryExecute(TccContext context) throws Exception;

    void confirm(TccContext context);

    void cancel(TccContext context);
}
