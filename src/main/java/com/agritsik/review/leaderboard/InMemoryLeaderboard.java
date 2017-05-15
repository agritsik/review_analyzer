package com.agritsik.review.leaderboard;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryLeaderboard implements Leaderboard {

    HashMap<String, Integer> map = new HashMap<>();

    @Override
    public Map<String, Integer> top() {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    @Override
    public void incrementBy(String key) {
        map.putIfAbsent(key, 0);
        map.put(key, map.get(key) + 1);
    }

    @Override
    public String toString() {
        return "InMemoryLeaderboard{" +
                "map=" + map +
                '}';
    }
}
