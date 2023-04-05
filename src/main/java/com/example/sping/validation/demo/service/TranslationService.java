package com.example.sping.validation.demo.service;

import org.springframework.stereotype.Service;

import static com.example.sping.validation.demo.config.Translator.toLocale;

@Service
public class TranslationService {

    public String getTranslation(String code){
        return toLocale(code);
    }
}
