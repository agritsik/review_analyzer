package com.agritsik.review;

import com.agritsik.review.context.PlayerContext;
import com.agritsik.review.context.TranslatorContext;
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
    private PlayerContext playerContext;
    private WordContext wordContext;
    private TranslatorContext translatorContext;

    @Autowired
    public Flow(Observable<String> source, PlayerContext playerContext, WordContext wordContext, TranslatorContext translatorContext) {
        this.source = source;
        this.playerContext = playerContext;
        this.wordContext = wordContext;
        this.translatorContext = translatorContext;
    }


    void run() throws URISyntaxException, IOException {

        ConnectableObservable<String> observable = source.publish();

        observable
                .subscribeOn(Schedulers.newThread())
                .subscribe(playerContext::addReview);

        observable
                .subscribeOn(Schedulers.computation())
                .subscribe(wordContext::addSentence);

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
