import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 一个内存泄漏例子
 */
public class MemoryLeakDemo {


    public static void main(String[] args) throws InterruptedException {

        List<int[]> list = new ArrayList<int[]>();
        List<MambaBean> list2 = new ArrayList<>();

        Runtime run = Runtime.getRuntime();
        int i = 1;
        while (true) {
            int[] arr = new int[1024 * 8];

            list2.add(new MambaBean(Arrays.toString(arr)));
//            list.add(arr);
            if (i++ % 1000 == 0) {
                System.out.print("i=" + i);
                System.out.print("最大内存=" + run.maxMemory() / 1024 / 1024 + "M,");
                System.out.print("已分配内存=" + run.totalMemory() / 1024 / 1024 + "M,");
                System.out.print("剩余空间内存=" + run.freeMemory() / 1024 / 1024 + "M");
                System.out.println("最大可用内存=" + (run.maxMemory() - run.totalMemory() + run.freeMemory()) / 1024 / 1024 + "M");
                Thread.sleep(1 * 1000L);
            }
        }
    }
}
