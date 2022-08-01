package com.zm.concurrency;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerDemo {
    private static Exchanger<List<String>> exchanger = new Exchanger();

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        threadPool.execute(new Worker(Lists.newArrayList("a", "b", "c")));

        threadPool.execute(new Worker(Lists.newArrayList("d", "e", "f")));


    }


    private static class Worker implements Runnable {

        private List<String> list = new ArrayList<>();

        public Worker(List<String> list) {
            this.list = list;
        }

        @Override
        public void run() {
            // 模拟执行耗时
            ThreadUtil.sleep(RandomUtil.randomInt(3000));

            List<String> retList = new ArrayList<>();
            try {
                retList = exchanger.exchange(list);

                retList.addAll(list);

                System.out.println("done:" + retList);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
