package com.agritsik.review;

import com.agritsik.review.context.ProductContext;
import com.agritsik.review.context.TranslatorContext;
import com.agritsik.review.context.UserContext;
import com.agritsik.review.context.WordContext;
import com.agritsik.review.dictionary.InMemoryDictionary;
import com.agritsik.review.leaderboard.InMemoryLeaderboard;
import com.agritsik.review.tokenizer.SimpleTokenizer;
import com.agritsik.review.translator.GoogleTranslator;
import io.reactivex.Observable;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FlowTest {

    @Test
    public void testRun() throws Exception {

        // arrange
        Observable<String> observable = fileObservable();

        UserContext userContext = new UserContext(new InMemoryLeaderboard());
        ProductContext productContext = new ProductContext(new InMemoryLeaderboard());
        WordContext wordContext = new WordContext(new SimpleTokenizer(), new InMemoryLeaderboard());
        TranslatorContext translatorContext = new TranslatorContext(new InMemoryDictionary(), new GoogleTranslator());
        Flow flow = new Flow(observable, userContext, productContext, wordContext, translatorContext);

        // act
        flow.run();

        // assert
        System.out.println(userContext.getTopUsers());
        System.out.println(productContext.getTopProducts());
        System.out.println(wordContext.getTopWords());
        System.out.println(translatorContext.tranlsate("If I were you"));

    }

    private Observable<String> fileObservable() throws URISyntaxException, IOException {
        Path path = Paths.get(ClassLoader.getSystemResource("1.txt").toURI());
        Stream<String> stream = Files.lines(path).skip(1);
        return Observable.fromIterable(stream::iterator);
    }

}