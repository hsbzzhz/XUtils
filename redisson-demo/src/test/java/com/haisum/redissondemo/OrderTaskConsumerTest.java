package com.haisum.redissondemo;

import com.haisum.redissondemo.model.OrderTask;
import com.haisum.redissondemo.producer.OrderTaskProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderTaskConsumerTest {

    @Autowired
    private OrderTaskProducer producer;

    @Test
    public void testDelayQueue() throws InterruptedException {
        producer.submitOrder(new OrderTask("ORD-001", "iPhone13", System.currentTimeMillis(), 5000));
        producer.submitOrder(new OrderTask("ORD-002", "MacBook", System.currentTimeMillis(), 10000));
        producer.submitOrder(new OrderTask("ORD-003", "AirPods", System.currentTimeMillis(), 3000));

        // 保持测试进程运行
        Thread.sleep(15000);
    }
}