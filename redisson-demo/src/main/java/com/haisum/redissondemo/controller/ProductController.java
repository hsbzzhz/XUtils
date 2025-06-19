package com.haisum.redissondemo.controller;


import com.haisum.redissondemo.service.ProductService;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: Hongzhi Zhang
 * @Date: 2025/6/19
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private ProductService productService;

    @GetMapping("/seckill/{productId}")
    public void seckill(@PathVariable String productId) {
        String lockKey = "lock:product:" + productId;
        RLock lock = redissonClient.getLock(lockKey);
        try {
            if (lock.tryLock(10, 30, TimeUnit.SECONDS)) {
                boolean status = productService.reduceStock(productId);
                if (status) {
                    System.out.println("秒杀成过！");
                } else {
                    System.out.println("库存不足！");
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
