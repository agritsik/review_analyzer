package com.agritsik.review.rest;

import com.agritsik.review.Flow;
import com.agritsik.review.context.PlayerContext;
import com.agritsik.review.context.TranslatorContext;
import com.agritsik.review.context.WordContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class IndexController {

    @Autowired
    private Flow flow;

    @Autowired
    private PlayerContext playerContext;

    @Autowired
    private TranslatorContext translatorContext;

    @Autowired
    WordContext wordContext;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "hello";
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public ResponseEntity<String> load() throws IOException, URISyntaxException {
        flow.run();
        return ResponseEntity.ok("completed");
    }

    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public List<String> players() {
        return playerContext.getTopPlayers();
    }

    @RequestMapping(value = "/words", method = RequestMethod.GET)
    public List<String> words() {
        return wordContext.getTopWords();
    }

    @RequestMapping(value = "/translate", method = RequestMethod.POST)
    public ResponseEntity translate(@RequestBody TranslateRequest request) {
        String translated = translatorContext.tranlsate(request.getText());
        return (translated == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(translated);
    }

    public static class TranslateRequest {
        String text;

        public TranslateRequest() {
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

}
