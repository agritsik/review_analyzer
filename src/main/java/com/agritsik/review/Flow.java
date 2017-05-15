package com.agritsik.review;

import com.agritsik.review.context.ProductContext;
import com.agritsik.review.context.TranslatorContext;
import com.agritsik.review.context.UserContext;
import com.agritsik.review.context.WordContext;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.Executors;

@Service
public class Flow {

    private Observable<String> source;
    private UserContext userContext;
    private ProductContext productContext;
    private WordContext wordContext;
    private TranslatorContext translatorContext;

    @Autowired
    public Flow(Observable<String> source, UserContext userContext, ProductContext productContext, WordContext wordContext, TranslatorContext translatorContext) {
        this.source = source;
        this.userContext = userContext;
        this.productContext = productContext;
        this.wordContext = wordContext;
        this.translatorContext = translatorContext;
    }

    public void run() throws URISyntaxException, IOException {

        ConnectableObservable<String> observable = source.publish();

        observable
                .subscribeOn(Schedulers.newThread()) // in a separate Thread
                .subscribe(userContext::addReview);

        observable
                .subscribeOn(Schedulers.newThread()) // in a separate Thread
                .subscribe(productContext::addReview);

        observable
                .subscribeOn(Schedulers.computation()) // use all available CPUs
                .subscribe(wordContext::addSentence);

        // Fixed thread pool. If we didn't have limitation, I would use IoScheduler though.
        Scheduler scheduler = Schedulers.from(Executors.newFixedThreadPool(100));
        observable.flatMap(i -> Observable.just(i)
                .subscribeOn(scheduler)
                .map(s -> {
                    translatorContext.addText(s);
                    return s;
                })
        ).subscribe();

        observable.connect();

    }

}
