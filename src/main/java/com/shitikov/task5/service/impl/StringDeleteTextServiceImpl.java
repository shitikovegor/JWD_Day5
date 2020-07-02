package com.shitikov.task5.service.impl;

import com.shitikov.task5.exception.ProjectException;
import com.shitikov.task5.service.DeleteTextService;

public class StringDeleteTextServiceImpl implements DeleteTextService {
    private static final String PUNCT_REGEX = "\\s*\\p{Punct}*\\s+";
    private static final String SPACE = "\\s+";
    private static final String VOWEL_REGEX = "[aeiouаоуыэяёюие]";
    private static final String SYMBOL_REGEX = "[\\p{Digit}]|\\p{P}(?<!\\p{Alpha}-)(?<!\\p{Alpha}')";

    @Override
    public String deleteNotLetters(String text) throws ProjectException{
        if (text == null) {
            throw new ProjectException("Invalid parameters");
        }

        String result = text.replaceAll(SYMBOL_REGEX, " ");

        return result;
    }

    @Override
    public String deleteWords(String text, int length) throws ProjectException {
        if (text == null || length < 0 || length > text.length()) {
            throw new ProjectException("Invalid parameters");
        }
        StringBuilder result = new StringBuilder(text);
        String[] words = text.split(PUNCT_REGEX);

        int startOfSearch = 0;
        int startOfWord;
        int endOfWord;

        for (String word : words) {
            startOfWord = result.indexOf(word, startOfSearch);
            endOfWord = startOfWord + word.length();
            String letterForCheck = Character.toString(word.charAt(0)).toLowerCase();
            if (length == word.length() && !letterForCheck.matches(VOWEL_REGEX)) {
                result.delete(startOfWord, endOfWord);
                startOfSearch = startOfWord;
            } else {
                startOfSearch = endOfWord;
            }
        }
        return result.toString();
    }
}
