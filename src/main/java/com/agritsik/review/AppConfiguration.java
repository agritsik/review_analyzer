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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Configuration
public class AppConfiguration {

    @Bean
    UserContext userContext() {
        return new UserContext(new InMemoryLeaderboard());
    }

    @Bean
    ProductContext productContext() {
        return new ProductContext(new InMemoryLeaderboard());
    }

    @Bean
    WordContext wordContext() {
        return new WordContext(new SimpleTokenizer(), new InMemoryLeaderboard());
    }

    @Bean
    TranslatorContext translatorContext() {
        return new TranslatorContext(new InMemoryDictionary(), new GoogleTranslator());
    }

    @Bean
    Observable<String> source() throws IOException, URISyntaxException {
        Path path = Paths.get("/tmp/1.txt");
        Stream<String> stream = Files.lines(path).skip(1);
        return Observable.fromIterable(stream::iterator);
    }

}
