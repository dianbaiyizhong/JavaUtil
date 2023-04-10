package com.nntk.pool;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class MyObjectPool extends GenericObjectPool<MyObject> {

    public MyObjectPool(PooledObjectFactory<MyObject> factory, GenericObjectPoolConfig<MyObject> config) {
        super(factory, config);
    }
}
