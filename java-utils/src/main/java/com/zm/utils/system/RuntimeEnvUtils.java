package com.zm.utils.system;

import java.io.File;

public class RuntimeEnvUtils {


    public static boolean isJar(Class clazz) {
        File file = new File(clazz.getProtectionDomain().getCodeSource().getLocation().getPath());
        return file.isFile();
    }



}
