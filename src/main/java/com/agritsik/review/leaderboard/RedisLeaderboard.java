package com.agritsik.review.leaderboard;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Map;

public class RedisLeaderboard implements Leaderboard {

    @Override
    public Map<String, Integer> top() {
        throw new NotImplementedException();
    }

    @Override
    public void incrementBy(String key) {
        throw new NotImplementedException();
    }

}
