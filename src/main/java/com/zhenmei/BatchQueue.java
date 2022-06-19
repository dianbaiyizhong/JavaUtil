package com.zhenmei;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * java 实现数据批量处理，达到一定数量或者达到一定时间去处理
 *
 * @param <T>
 */
public class BatchQueue<T> {

    private final int batchSize;
    private final Consumer<List<T>> consumer;
    private final int timeoutInMs;

    private AtomicBoolean isLooping = new AtomicBoolean(false);
    private BlockingQueue<T> queue = new LinkedBlockingQueue<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();

    private AtomicLong start = new AtomicLong(System.currentTimeMillis());

    public BatchQueue(int batchSize, int timeoutInMs, Consumer<List<T>> consumer) {
        this.batchSize = batchSize;
        this.timeoutInMs = timeoutInMs;
        this.consumer = consumer;
    }

    public BatchQueue(int batchSize, Consumer<List<T>> consumer) {
        this(batchSize, 500, consumer);
    }

    public boolean add(T t) {
        boolean result = queue.add(t);
        if (!isLooping.get() && result) {
            isLooping.set(true);
            startLoop();
        }
        return result;
    }

    public void completeAll() {
        while (!queue.isEmpty()) {
            drainToConsume();
        }
    }

    private void startLoop() {
        executorService.execute(new ExeThread());
    }

    private void drainToConsume() {
        List<T> drained = new ArrayList<>();
        int num = queue.drainTo(drained, batchSize);
        if (num > 0) {
            consumer.accept(drained);
            start.set(System.currentTimeMillis());
        }
    }

    private class ExeThread implements Runnable {
        @Override
        public void run() {
            start = new AtomicLong(System.currentTimeMillis());
            while (true) {
                long last = System.currentTimeMillis() - start.get();
                if (queue.size() >= batchSize || (!queue.isEmpty() && last > timeoutInMs)) {
                    drainToConsume();
                } else if (queue.isEmpty()) {
                    isLooping.set(false);
                    break;
                }
            }
        }
    }

}