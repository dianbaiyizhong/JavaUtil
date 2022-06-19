import cn.hutool.core.thread.ThreadUtil;
import com.google.common.collect.Lists;
import com.zhenmei.BatchQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class TestBatchQueue {
    static BatchQueue<String> batchQueue = new BatchQueue<>(50, 5000, x -> exe(x));

    public static void main(String[] args) {

        for (int i = 0; i < 501; i++) {

            batchQueue.add("line");

        }


        ThreadUtil.sleep(10000);
        batchQueue.completeAll();

    }

    private static void exe(List<String> o) {
        System.out.println("处理数据：" + o.size());
    }
}
