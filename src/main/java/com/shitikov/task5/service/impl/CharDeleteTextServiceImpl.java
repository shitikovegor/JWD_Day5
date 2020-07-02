package com.shitikov.task5.service.impl;

import com.shitikov.task5.exception.ProjectException;
import com.shitikov.task5.service.DeleteTextService;

import java.util.ArrayList;

public class CharDeleteTextServiceImpl implements DeleteTextService {
    private static final char[] VOWELS = new char[]{'a', 'e', 'i', 'o', 'u', 'а', 'о', 'у', 'ы', 'э',
            'я', 'ё', 'ю', 'и', 'е'};

    @Override
    public String deleteNotLetters(String text) throws ProjectException {
        if (text == null) {
            throw new ProjectException("Invalid parameters");
        }
        char[] textChar = text.toCharArray();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length() - 1; i++) {

            if (Character.isLetter(textChar[i]) || Character.isSpaceChar(textChar[i])) {
                result.append(textChar[i]);
            } else if (textChar[i] == '-' || textChar[i] == '\'') {
                if (Character.isLetter(textChar[i + 1])) {
                    result.append(textChar[i]);
                }
            } else {
                result.append(" ");
            }
        }
        char lastCharacter = textChar[textChar.length - 1];

        if (Character.isLetter(lastCharacter)) {
            result.append(lastCharacter);
        }
        return result.toString();
    }

    @Override
    public String deleteWords(String text, int length) throws ProjectException {
        if (text == null || length < 0 || length > text.length()) {
            throw new ProjectException("Invalid parameters");
        }
        char[] textChar = text.toCharArray();
        ArrayList<Character> word = new ArrayList<>();
        StringBuilder result = new StringBuilder();


        for (int i = 0; i < textChar.length; i++) {
            while (Character.isLetterOrDigit(textChar[i])) {
                word.add(textChar[i]);
                i++;
            }
            if (!word.isEmpty()) {
                if ((word.size() != length) && !isVowel(word.get(0))) {
                    result.append(listToString(word));
                }
                result.append(textChar[i]);
                word.clear();
            }

        }
        return null;
    }

    private boolean isVowel(char checkChar) {
        for (char vowel : VOWELS) {
            if (checkChar == vowel) {
                return true;
            }
        }
        return false;
    }

    // TODO: 02.07.2020 import method or dublicate or Class Utils?
    private String listToString(ArrayList<Character> word) {
        StringBuilder result = new StringBuilder();

        for (char letter : word) {
            result.append(letter);
        }
        return result.toString();
    }
}
