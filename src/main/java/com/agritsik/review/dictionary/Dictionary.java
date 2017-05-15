package com.agritsik.review.dictionary;

public interface Dictionary {
    void add(String origin, String translated);

    String translate(String origin);
}
