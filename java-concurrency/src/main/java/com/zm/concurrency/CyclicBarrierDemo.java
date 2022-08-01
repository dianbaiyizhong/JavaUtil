package com.zm.concurrency;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {


        /**
         * 凑齐了N个任务后，再讲这N个任务进行并发处理，而且跟CountDownLatch相比，还能重置计数器哦
         */
        new CyclicBarrierDemo().demo1();


    }

    private void demo1() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("start...");
                ThreadUtil.sleep(RandomUtil.randomInt(3000));
                System.out.println("end...");
            }).start();
        }


        System.out.println("不等待，直接到这里...");
        // 前面堵塞了三个任务，还差一个任务，CyclicBarrier才能执行
        ThreadUtil.sleep(3000);
        System.out.println("投递第4个任务");
        new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("start...");
            ThreadUtil.sleep(RandomUtil.randomInt(3000));
            System.out.println("end...");
        }).start();



    }
}
