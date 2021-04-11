import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class TestRxJava {

    public static void main(String[] args) {
        final Observable<String> memory = Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<String> observableEmitter) throws Throwable {

                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
        final Observable<String> disk = Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<String> observableEmitter) throws Throwable {

//                observableEmitter.onNext("disk");
                observableEmitter.onComplete();

            }
        }).subscribeOn(Schedulers.io());

        Observable<String> network = Observable.just("network");


        //主要就是靠concat operator来实现
        Observable.concat(memory, disk, network)
                .subscribeOn(Schedulers.newThread())
                .subscribe(s -> {
                    System.out.println("--------------subscribe: " + s);
                });


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        //https://blog.csdn.net/finalheart/article/details/87615546
        CompletableFuture completableFuture = new CompletableFuture<>();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                completableFuture.complete("s");

            }

        }).start();

        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
