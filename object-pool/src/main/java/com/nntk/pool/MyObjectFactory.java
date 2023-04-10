package com.nntk.pool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

@Slf4j
public class MyObjectFactory implements PooledObjectFactory<MyObject> {
    /**
     * 调用获取对象方法前被调用
     * 此方法一般进行一些前置操作
     */
    @Override
    public void activateObject(PooledObject<MyObject> pooledObject) throws Exception {

    }

    @Override
    public void destroyObject(PooledObject<MyObject> pooledObject) throws Exception {
        MyObject object = pooledObject.getObject();
        log.info("做一些销毁的事情...");
    }

    @Override
    public PooledObject<MyObject> makeObject() throws Exception {
        DefaultPooledObject<MyObject> object = new DefaultPooledObject<>(new MyObject());
        log.info("创建对象-->{}",object.getObject().getName());
        return object;
    }

    /**
     * 当还回对象并且validateObject方法返回true后被调用
     * 一般在此方法中对刚刚使用完成的对象进行重置
     */
    @Override
    public void passivateObject(PooledObject<MyObject> pooledObject) throws Exception {

    }

    @Override
    public boolean validateObject(PooledObject<MyObject> pooledObject) {
        return true;
    }
}
