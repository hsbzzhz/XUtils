package com.haisum.redissondemo.consumer;


import com.haisum.redissondemo.model.OrderTask;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * @Description:
 * @Author: Hongzhi Zhang
 * @Date: 2025/6/19
 */

@Component
public class OrderTaskConsumer {
    private static final String DELAY_QUEUE_NAME = "order:delay_queue";
    private final RedissonClient redisson;
    public OrderTaskConsumer(RedissonClient redisson) {
        this.redisson = redisson;
    }

    @PostConstruct
    public void startConsume() {
        new Thread(this::consume).start();
    }
    private void consume() {
        RBlockingDeque<OrderTask> queue = redisson.getBlockingDeque(DELAY_QUEUE_NAME);
        while (true) {
            try {
                OrderTask task = queue.take();
                processTask(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    private void processTask(OrderTask task) {
        System.out.printf("Consumer: 处理超时订单 %s, 商品: %s, 创建时间: %d%n",
                task.getOrderId(),
                task.getProductName(),
                task.getCreateTime());

    }
}
