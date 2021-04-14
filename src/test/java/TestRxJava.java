import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.After;
import org.junit.Test;


public class TestRxJava {


    /**
     * 前面任何一个满足条件，后面都不会执行
     */
    @Test
    public void testConcat() {

        Observable<String> memory = Observable.create((ObservableOnSubscribe<String>) emitter -> {
            // emitter.onComplete();
            emitter.onNext("memory");
        }).subscribeOn(Schedulers.io());
        Observable<String> disk = Observable.create((ObservableOnSubscribe<String>) emitter -> {
            // emitter.onComplete();
            emitter.onNext("disk");

        }).subscribeOn(Schedulers.io());


        Observable.concat(memory, disk)
                .subscribeOn(Schedulers.newThread())
                .subscribe(s -> {
                    System.out.println(s);
                });


    }


    /**
     * 将多个事件合并成一个
     */
    @Test
    public void testMerge() {
        Observable<String> memory = Observable.create((ObservableOnSubscribe<String>) emitter -> {
            // emitter.onComplete();
            emitter.onNext("memory");
        }).subscribeOn(Schedulers.newThread());
        Observable<String> disk = Observable.create((ObservableOnSubscribe<String>) emitter -> {
            // emitter.onComplete();
            Thread.sleep(5000);
            emitter.onNext("disk");
        }).subscribeOn(Schedulers.newThread());

        Observable.merge(memory, disk)
                .subscribeOn(Schedulers.newThread())
                .subscribe(s -> {
                    System.out.println(s);
                });
    }


    /**
     * 等待多个任务执行结束
     */
    @Test
    public void testZip() {
        Observable<String> memory = Observable.create((ObservableOnSubscribe<String>) emitter -> {
            // emitter.onComplete();
            emitter.onNext("memory");
        }).subscribeOn(Schedulers.newThread());
        Observable<String> disk = Observable.create((ObservableOnSubscribe<String>) emitter -> {
            // emitter.onComplete();
            Thread.sleep(5000);
            emitter.onNext("disk");
        }).subscribeOn(Schedulers.newThread());

        Observable.zip(memory, disk, (a, b) -> a + b).subscribe(o ->
                System.out.println(o)
        );
    }


    @Test
    public void testOther() {
        Observable.just("1", "2", "2", "3", "4", "5")
                .map(Integer::parseInt)
                .filter(s -> s > 1)
                .distinct()
                .take(3)  // 只要前3项
                .reduce((integer, integer2) -> integer.intValue() + integer2.intValue())
                .subscribe(System.out::println);
    }

    @After
    public void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
