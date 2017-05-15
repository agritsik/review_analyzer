package com.agritsik.review.leaderboard;

import java.util.Map;

public interface Leaderboard {

    Map<String, Integer> top();

    void incrementBy(String key);

}
