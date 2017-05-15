package com.agritsik.review.rx;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.Executors;

public class RxTest {

    @Test
    public void testRx() throws Exception {

        Observable<Integer> observable = Observable.range(0, 10);

        observable
                .subscribeOn(Schedulers.newThread())
                .subscribe(s -> log("add to leaderbord", s));

//        Scheduler scheduler = Schedulers.io();
        Scheduler scheduler = Schedulers.from(Executors.newFixedThreadPool(100));
        observable.flatMap(i -> Observable.just(i)
                .subscribeOn(scheduler)
                .map(this::imitateHttpRequest)
        ).subscribe(i -> log("translate", i));

        Thread.sleep(6000);
    }

    private Integer imitateHttpRequest(Integer i) {
        try {
            Thread.sleep(new Random().nextInt(3) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }

    private void log(String message, Integer i) {
        String r = String.format("%s : %s : %s", Thread.currentThread().getName(), message, i);
        System.out.println(r);
    }
}
