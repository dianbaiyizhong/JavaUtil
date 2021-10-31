package com.zhenmei;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

public class FileSystemPool extends BasePooledObjectFactory<FileSystem> {
    @Override
    public FileSystem create() throws Exception {
        return FileSystem.newInstance(new Configuration());
    }

    @Override
    public PooledObject<FileSystem> wrap(FileSystem fileSystem) {
        return new DefaultPooledObject<>(fileSystem);
    }
}
