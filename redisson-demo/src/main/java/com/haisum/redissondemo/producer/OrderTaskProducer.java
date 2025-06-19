package com.haisum.redissondemo.producer;


import com.haisum.redissondemo.model.OrderTask;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: Hongzhi Zhang
 * @Date: 2025/6/19
 */

@Component
public class OrderTaskProducer {
    private static final String DELAY_QUEUE_NAME = "order:delay_queue";
    private final RedissonClient redisson;
    public OrderTaskProducer(RedissonClient redisson) {
        this.redisson = redisson;
    }
    public void submitOrder(OrderTask order) {
        RDelayedQueue<OrderTask> delayedQueue = redisson.getDelayedQueue(
                redisson.getBlockingDeque(DELAY_QUEUE_NAME)
        );
        delayedQueue.offer(order, order.getExpireDelay(), TimeUnit.MILLISECONDS);
        System.out.printf("Producer: 已提交订单 %s, 将在 %dms 后处理%n",
                order.getOrderId(), order.getExpireDelay());
    }
}
