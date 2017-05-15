package com.agritsik.review.context;

import com.agritsik.review.leaderboard.InMemoryLeaderboard;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class UserContextTest {

    public static final String PREFIX = "A3SGXH7AUHU8G";

    @Test
    public void testTopPlayers() throws Exception {

        // arrange
        UserContext userContext = new UserContext(new InMemoryLeaderboard());
        new Random().ints(20, 0, 5).forEach(value ->
                userContext.addReview("1,B001E4KFG0," + PREFIX + value + ",delmartian"));

        // act
        List<String> topPlayers = userContext.getTopUsers();

        // assert
        System.out.println(topPlayers);
        Assert.assertTrue(topPlayers.stream()
                .allMatch(e -> e.startsWith(PREFIX)));
    }

}