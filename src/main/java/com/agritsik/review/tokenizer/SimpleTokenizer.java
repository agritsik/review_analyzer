package com.agritsik.review.tokenizer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleTokenizer implements Tokenizer {

    String[] SKIP = {
            "i", "is",
            "the", "a", "an", "this", "that",
            "to", "in", "on"
    };

    @Override
    public List<String> tokenize(String sentence) {
        return Arrays.stream(sentence.split(" "))
                .filter(s -> !s.trim().isEmpty())
                .map(String::toLowerCase)
                .filter(s -> Arrays.stream(SKIP).noneMatch(s::equals))
                .collect(Collectors.toList());
    }
}
