package com.nntk;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.benf.cfr.reader.api.CfrDriver;
import org.benf.cfr.reader.util.getopt.OptionsImpl;

public class CfrDemo {
    public static void main(String[] args) throws IOException {
        Long time = cfr("D:\\project\\bigdata\\bigdata-model\\spark-sql-model\\target\\spark-sql-model-1.0.jar", "./cfr_output_jar");
        System.out.println(String.format("decompiler time: %dms", time));
    }

    public static Long cfr(String source, String targetPath) throws IOException {
        Long start = System.currentTimeMillis();
        // source jar
        List<String> files = new ArrayList<>();
        files.add(source);
        // target dir
        HashMap<String, String> outputMap = new HashMap<>();
        outputMap.put("outputdir", targetPath);

        OptionsImpl options = new OptionsImpl(outputMap);
        CfrDriver cfrDriver = new CfrDriver.Builder().withBuiltOptions(options).build();
        cfrDriver.analyse(files);
        Long end = System.currentTimeMillis();
        return (end - start);
    }
}
