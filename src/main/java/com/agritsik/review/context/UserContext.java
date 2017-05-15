package com.agritsik.review.context;

import com.agritsik.review.leaderboard.Leaderboard;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserContext {
    private Leaderboard leaderboard;

    public UserContext(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }

    public List<String> getTopUsers(){
        System.out.println(this.leaderboard.top());
        return this.leaderboard.top().entrySet().stream()
                .map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public void addReview(String s){
        leaderboard.incrementBy(s.split(",")[2]);
    }
}
