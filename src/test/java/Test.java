import com.zhenmei.OptionalBean;

import java.util.Optional;

public class Test {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {

        System.out.println(new Integer(42) == new Integer(42));
    }
}