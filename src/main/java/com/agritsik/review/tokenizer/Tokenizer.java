package com.agritsik.review.tokenizer;

import java.util.List;

public interface Tokenizer {

    List<String> tokenize(String sentence);
}
