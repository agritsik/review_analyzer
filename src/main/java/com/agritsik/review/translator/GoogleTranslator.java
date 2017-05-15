package com.agritsik.review.translator;

public class GoogleTranslator implements Translator {

    @Override
    public String translate(String text){

        // todo: Spring RestTemplate will be here ...
        // todo: Circuit Breaker (e.g. Netflix's Hystrix) will be useful here as well ...

        return "je ne parle pas francais";
    }
}
