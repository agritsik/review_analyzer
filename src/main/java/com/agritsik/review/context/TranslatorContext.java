package com.agritsik.review.context;

import com.agritsik.review.dictionary.Dictionary;
import com.agritsik.review.translator.Translator;

public class TranslatorContext {

    private Dictionary dictionary;
    private Translator translator;

    public TranslatorContext(Dictionary dictionary, Translator translator) {
        this.dictionary = dictionary;
        this.translator = translator;
    }

    public void addText(String s) {
        String sentence = s.split(",")[9];
        dictionary.add(sentence, translator.translate(sentence));
    }

    public String tranlsate(String s) {
        return dictionary.translate(s);
    }

}
