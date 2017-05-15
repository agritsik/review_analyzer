package com.agritsik.review.leaderboard;

import com.agritsik.review.leaderboard.InMemoryLeaderboard;
import org.junit.Test;

import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * Created by andrey on 5/13/17.
 */
public class InMemoryLeaderboardTest {


    @Test
    public void testTop() throws Exception {

        // arrange
        InMemoryLeaderboard leaderboard = new InMemoryLeaderboard();
        new Random().ints(20, 0, 5).forEach(value -> leaderboard.incrementBy("user_" + Integer.toString(value)));

        // act
        Map<String, Integer> result = leaderboard.top();

        // assert
        Integer prev = null;
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            if (prev != null) assertTrue(entry.getValue() <= prev);
            prev = entry.getValue();
        }
    }

}