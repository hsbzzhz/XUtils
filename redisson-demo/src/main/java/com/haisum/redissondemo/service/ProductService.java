package com.haisum.redissondemo.service;


import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Hongzhi Zhang
 * @Date: 2025/6/19
 */

@Service
public class ProductService {

    private RedissonClient redisson;

    public void initProductStock(String productId) {
        RAtomicLong atomicLong = redisson.getAtomicLong("product:" + productId + ":stock");
        atomicLong.set(100L);
    }

    // 秒杀服务
    public boolean reduceStock(String productId) {
        String stockKey = "product:" + productId + ":stock";
        RAtomicLong stock = redisson.getAtomicLong(stockKey);
        if (stock == null) {
            return false;
        }
        Long currentStock = stock.get();
        if (currentStock == null || currentStock <= 0) {
            return false;
        }

        long newStock = stock.decrementAndGet();
        if (newStock < 0) {
            stock.set(0);      // 校正库存为 0（防止变成负数）
            return false;
        }
        // 扣减成功，创建订单...

        return true;
    }

}
