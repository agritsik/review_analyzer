package com.agritsik.review.rx;

import io.reactivex.Observable;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileObservableTest {


    @Test
    public void name() throws Exception {

        Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource("1.txt").toURI()));
        Observable.fromIterable(stream::iterator).subscribe(s -> {
            System.out.println(">>");
            System.out.println(s);
        });


    }
}
