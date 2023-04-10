package com.nntk.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.time.Duration;

public class Test {
    public static void main(String[] args) throws Exception {


        new Test().run();

    }


    public void run() throws Exception {
        MyObjectPool pool = myObjectPool();
        for (int i = 0; i < 10; i++) {
            MyObject myObject = pool.borrowObject();
            System.out.println(myObject);
        }
    }


    protected MyObjectPool myObjectPool() {
        MyObjectFactory objectFactory = new MyObjectFactory();
        //设置对象池的相关参数
        GenericObjectPoolConfig<MyObject> poolConfig = new GenericObjectPoolConfig<>();

        // 最大空闲数
        poolConfig.setMaxIdle(2);
        //最小空闲,设置为2表示池内至少存放2个空闲对象(当池内有2个空闲对象时调用borrowObject去对象时会立即调用创建对象的方法保证池内有2个空闲对象)
        poolConfig.setMinIdle(2);
        //最大总数 10
        poolConfig.setMaxTotal(10);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        poolConfig.setMinEvictableIdleTime(Duration.ofMillis(30));
        // 多久执行一次对象扫描，将无用的对象销毁，默认-1不扫描
        poolConfig.setTimeBetweenEvictionRuns(Duration.ofMillis(30));
        // 在获取对象的时候检查有效性, 默认false
        poolConfig.setTestOnBorrow(true);
        // 在归还对象的时候检查有效性, 默认false
        poolConfig.setTestOnReturn(false);
        // 在空闲时检查有效性, 默认false
        poolConfig.setTestWhileIdle(false);
        // 最大等待时间， 默认的值为-1，表示无限等待。
        poolConfig.setMaxWait(Duration.ofSeconds(1));
        // 是否启用后进先出, 默认true
        poolConfig.setLifo(true);
        // 连接耗尽时是否阻塞, false立即抛异常,true阻塞直到超时, 默认true
        poolConfig.setBlockWhenExhausted(true);
        // 每次逐出检查时 逐出的最大数目 默认3
        poolConfig.setNumTestsPerEvictionRun(3);

        //一定要关闭jmx，不然springboot启动会报已经注册了某个jmx的错误
        poolConfig.setJmxEnabled(false);

        //新建一个对象池,传入对象工厂和配置
        MyObjectPool pool = new MyObjectPool(objectFactory, poolConfig);

        initPool(2, 5);
        return pool;
    }

    /**
     * 预先加载testObject对象到对象池中
     *
     * @param initialSize 初始化连接数
     * @param maxIdle     最大空闲连接数
     */
    private void initPool(int initialSize, int maxIdle) {
        if (initialSize <= 0) {
            return;
        }
        for (int i = 0; i < Math.min(initialSize, maxIdle); i++) {

        }
    }


}
