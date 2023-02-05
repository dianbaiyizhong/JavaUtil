package com.nntk;

import cn.hutool.core.lang.ClassScanner;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.ClassLoaderUtil;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Set;
import java.util.function.Consumer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ScanningJar {

    public static void main(String[] args) throws IOException {

        Set<Class<?>> classes = ClassScanner.scanPackage("com.bigdata");

        classes.forEach(new Consumer<Class<?>>() {
            @Override
            public void accept(Class<?> aClass) {
                System.out.println(aClass.getName());
            }
        });
    }

    private void scanJar(URL url) throws IOException {
        URLConnection urlConn = url.openConnection();
        if (urlConn instanceof JarURLConnection) {
            JarURLConnection jarUrlConn = (JarURLConnection) urlConn;
            try (JarFile jarFile = jarUrlConn.getJarFile()) {
                Enumeration<JarEntry> jarFileEntries = jarFile.entries();
                while (jarFileEntries.hasMoreElements()) {
                    JarEntry jarEntry = jarFileEntries.nextElement();
                    String en = jarEntry.getName();
                    // 只扫描 basePackage 之下的类

                    System.out.println(en);
                }
            }
        }
    }


}
