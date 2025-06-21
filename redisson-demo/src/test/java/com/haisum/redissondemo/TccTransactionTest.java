package com.haisum.redissondemo;

import com.haisum.redissondemo.common.tcc.TccCoordinator;
import com.haisum.redissondemo.common.tcc.TccParticipant;
import com.haisum.redissondemo.service.impl.InventoryServiceImpl;
import com.haisum.redissondemo.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TccTransactionTest {

    @Test
    void orderTransaction() {

        // 创建参与者
        TccParticipant order = new OrderServiceImpl("O1001");
        TccParticipant inventory = new InventoryServiceImpl("P1001", 2);

        //事务协调
        TccCoordinator coordinator = new TccCoordinator(
                List.of(order, inventory),
                "TX202506211305"
        );

        // 执行事务
        try {
            coordinator.execute();
            System.out.println("事务提交！");
        }catch (Exception e) {
            System.out.println("事务执行失败" +e.getMessage());
        }
    }
}