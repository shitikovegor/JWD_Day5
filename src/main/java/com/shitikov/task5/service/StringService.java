package com.shitikov.task5.service;

import com.shitikov.task5.exception.ProjectException;

public class StringService {
    private static final char[] VOWELS = new char[]{'a', 'e', 'i', 'o', 'u', 'а', 'о', 'у', 'ы', 'э',
            'я', 'ё', 'ю', 'и', 'е'};
    private static final String SPLIT_REGEX = "\\s*\\p{Punct}*\\s+";

    public String changeCharacter(String text, int index, char changeChar) throws ProjectException {
        if (text == null || index < 0) {
            throw new ProjectException("Parameters are wrong");
        }
        StringBuilder result = new StringBuilder(text);

        String[] words = text.split(SPLIT_REGEX);
        int startOfSearch = 0;
        int startOfWord;

        for (String word : words) {
            if (index < word.length()) {
                startOfWord = result.indexOf(word, startOfSearch);
                int changeIndex = startOfWord + index;

                if (String.valueOf(result.charAt(changeIndex)).matches("[a-zA-Zа-яА-Я]")) {
                    result.setCharAt(changeIndex, changeChar);
                }
                startOfSearch += word.length();
            }
        }
        return result.toString();
    }

    public String checkText(String text) {
        StringBuilder builder = new StringBuilder(text);

        String result = text.replaceAll("[Рр]а", "ро");
// TODO: 29.06.2020 change P to P (now P -> p)
        return result;
    }

    public String changeWord(String text, int length, int startSubstring, int finishSubstring) throws ProjectException {
        if (!isValid(text, length, startSubstring, finishSubstring)) {
            throw new ProjectException("Parameters are wrong!");
        }

        StringBuilder result = new StringBuilder(text);
        String changeWord = text.substring(startSubstring, finishSubstring);

        String[] words = text.split(SPLIT_REGEX);
        int startOfSearch = 0;
        int startOfWord;
        int endOfWord;

        for (String word : words) {
            if (length <= word.length()) {
                startOfWord = result.indexOf(word, startOfSearch);
                endOfWord = startOfWord + word.length();

                if (word.length() == length) {
                    result.replace(startOfWord, endOfWord, changeWord);
                }

                startOfSearch = endOfWord;
            }
        }
        return result.toString();
    }

//    public String deleteSymbols(String text) {
//        StringBuilder result = new StringBuilder(text);
//        text.
//        result.
//    }

    public String deleteWords(String text, int length) {
        StringBuilder result = new StringBuilder(text);
        String[] words = text.split(SPLIT_REGEX);

        int startOfSearch = 0;
        int startOfWord;
        int endOfWord;

        for (String word : words) {
            if (length == word.length()) {
                startOfWord = result.indexOf(word, startOfSearch);
                endOfWord = startOfWord + word.length();

                if (!isVowel(Character.toLowerCase(word.charAt(0)))) {
                    result.delete(startOfWord, endOfWord);
                    startOfSearch = startOfWord;
                } else {
                    startOfSearch = endOfWord;
                }
            }
        }
        return result.toString();
    }

    private boolean isValid(String text, int length, int startIndex, int finishIndex) {

        if (text == null) {
            return false;
        }
        return (startIndex >= 0 && startIndex < text.length() &&
                finishIndex >= 0 && finishIndex < text.length() &&
                finishIndex >= startIndex && length > 0 && length < text.length());
    }

    private boolean isVowel(char checkChar) {
        for (char vowel : VOWELS) {
            if (checkChar == vowel) {
                return true;
            }
        }
        return false;
    }
}
