package com.zhenmei;

import cn.hutool.core.util.ServiceLoaderUtil;

/**
 * META-INF不会拷贝到target/classes下面，手动拷贝可观察到效果
 */
public class TestServiceLoader {


    public static void main(String[] args) {

        ITest test = ServiceLoaderUtil.loadFirstAvailable(ITest.class);

        test.test();
    }


}
