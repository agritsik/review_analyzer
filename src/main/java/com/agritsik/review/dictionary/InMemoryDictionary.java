package com.agritsik.review.dictionary;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDictionary implements Dictionary {

    Map<String, String> dictionary = new ConcurrentHashMap<>();
    @Override
    public void add(String origin, String translated) {
        dictionary.put(origin, translated);
    }

    @Override
    public String translate(String origin) {
        return dictionary.get(origin);
    }
}
