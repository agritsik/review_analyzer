package com.agritsik.review.context;

import com.agritsik.review.leaderboard.Leaderboard;
import com.agritsik.review.tokenizer.Tokenizer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordContext {
    private Leaderboard leaderboard;
    private Tokenizer tokenizer;

    public WordContext(Tokenizer tokenizer, Leaderboard leaderboard) {
        this.tokenizer = tokenizer;
        this.leaderboard = leaderboard;
    }

    public List<String> getTopWords(){
        System.out.println(this.leaderboard.top());
        return this.leaderboard.top().entrySet().stream()
                .map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public void addSentence(String s){
        String sentence = s.split(",")[9];
        tokenizer.tokenize(sentence).stream().forEach(leaderboard::incrementBy);
    }
}
