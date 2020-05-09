package com.leesin.sentineldemo;

import com.google.common.util.concurrent.RateLimiter;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: Leesin Dong
 * @date: Created in 2020/5/8 0008 20:20
 * @modified By:
 */
// guava api

// 令牌桶的实现
public class RateLimiterMain {
    RateLimiter rateLimiter = RateLimiter.create(10);//qps 每秒钟获取10个请求，也就是每秒钟获取10个令牌
    //这里的意思就是将这里的qps设置为每秒10个,超过这个数值,就进行限流
    public void doTest() {
        if (rateLimiter.tryAcquire()) { //这里就是获得一个令牌，成功获得了一个令牌
            System.out.println("允许通过进行访问");
        } else {
            System.out.printf("被限流了");
        }
    }

    public static void main(String[] args) throws IOException {
        RateLimiterMain rateLimiterMain = new RateLimiterMain();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    Thread.sleep(random.nextInt(1000));
                    rateLimiterMain.doTest();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.countDown();
        System.in.read();
    }
}
