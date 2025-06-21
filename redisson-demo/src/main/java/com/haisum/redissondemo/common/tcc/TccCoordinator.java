package com.haisum.redissondemo.common.tcc;

import java.util.List;

public class TccCoordinator {
    private List<TccParticipant> participantList;
    private TccContext tccContext;

    public TccCoordinator(List<TccParticipant> participantList, String xid) {
        this.participantList = participantList;
        this.tccContext = new TccContext(xid);
    }

    public void execute() {
        try{
            // try阶段
            for (TccParticipant participant : participantList) {
                participant.tryExecute(tccContext);
            }
            // confirm阶段
            tccContext.commit();
            for (TccParticipant participant : participantList) {
                participant.confirm(tccContext);
            }
        } catch (Exception e) {
            // 回滚
            for (TccParticipant participant : participantList) {
                participant.cancel(tccContext);
            }
            throw new RuntimeException("rollback: " + e);
        }
    }
}
