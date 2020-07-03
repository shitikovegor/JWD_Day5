package com.shitikov.task5.service.impl;

import java.util.ArrayList;

class TextService {
    static final String CHANGE_SYMBOL_REGEX = "([%s%s])([%s%s])";
    static final String CHANGE_GROUP = "$1%s";
    static final String PUNCT_REGEX = "\\s*\\p{Punct}*\\s+";
    static final String SPACE = "\\s+";
    static final String SYMBOL_REGEX = "[\\p{Digit}]|\\p{Punct}(?<!\\p{L}-)(?<!\\p{L}')";
//    protected static final String CHANGE_GROUP = "$1%s";
//    protected static final String LETTER_REGEX = "[a-zA-Zа-яА-ЯёЁ]";

    char[] getCasesOfLetter(char letter) {
        char[] cases = new char[2];
        char lowerCase;
        char upperCase;

        if (Character.isLowerCase(letter)) {
            lowerCase = letter;
            upperCase = Character.toUpperCase(letter);
        } else {
            lowerCase = Character.toLowerCase(letter);
            upperCase = letter;
        }

        cases[0] = lowerCase;
        cases[1] = upperCase;

        return cases;
    }

    String listToString(ArrayList<Character> word) {
        StringBuilder result = new StringBuilder();

        for (char letter : word) {
            result.append(letter);
        }
        return result.toString();
    }

    boolean isValidData(String text, int length, int startIndex, int finishIndex) {
        if (text == null) {
            return false;
        }
        return (startIndex >= 0 && startIndex < text.length() &&
                finishIndex >= 0 && finishIndex < text.length() &&
                finishIndex >= startIndex && length > 0 && length < text.length());
    }
}
