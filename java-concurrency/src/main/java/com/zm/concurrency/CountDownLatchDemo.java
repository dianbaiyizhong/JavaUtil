package com.zm.concurrency;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.concurrent.CountDownLatch;

/**
 * 当已知的N个数量的任务都执行结束，再执行下一步
 */
public class CountDownLatchDemo {


    public static void main(String[] args) throws InterruptedException {

        // 等待两个任务，所以参数输入2
        CountDownLatch countDownLatch = new CountDownLatch(4);


        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("start...");
                    ThreadUtil.sleep(RandomUtil.randomInt(3000));
                    // 任务执行结束后都要调用countDown()方法反馈
                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();
        System.out.println("all done...");
    }


}
