package com.agritsik.review.rest;

import com.agritsik.review.Flow;
import com.agritsik.review.context.ProductContext;
import com.agritsik.review.context.TranslatorContext;
import com.agritsik.review.context.UserContext;
import com.agritsik.review.context.WordContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class IndexController {

    @Autowired
    private Flow flow;

    @Autowired
    private UserContext userContext;
    @Autowired
    private ProductContext productContext;

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

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<String> users() {
        return userContext.getTopUsers();
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<String> products() {
        return productContext.getTopProducts();
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
