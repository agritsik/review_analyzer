package com.agritsik.review;

import com.agritsik.review.context.PlayerContext;
import com.agritsik.review.context.TranslatorContext;
import com.agritsik.review.context.WordContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;

//@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppIntegrationTests {

	@Autowired
	Flow flow;

	@Autowired
	PlayerContext playerContext;

	@Autowired
	WordContext wordContext;

	@Autowired
	TranslatorContext translatorContext;

	@Test
	public void contextLoads() throws IOException, URISyntaxException {
		flow.run();

		System.out.println(playerContext.getTopPlayers());
		System.out.println(wordContext.getTopWords());
		System.out.println(translatorContext.tranlsate("Love's it!,My 12 month old loves this salmon.  These containers are great for traveling and make easy meals!"));

	}



}
