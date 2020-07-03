package com.shitikov.task5.service.impl;

import com.shitikov.task5.exception.ProjectException;
import com.shitikov.task5.service.ChangeTextService;

import java.util.ArrayList;

public class CharChangeTextServiceImpl extends TextService implements ChangeTextService {

    @Override
    public String changeCharacter(String text, int index, char changeChar) throws ProjectException {
        if (text == null || index < 0) {
            throw new ProjectException("Invalid parameters");
        }

        char[] textChar = text.toCharArray();
        int wordIndex = 0;

        for (int i = 0; i < textChar.length; i++) {
            if (Character.isLetterOrDigit(textChar[i])) {
                if (wordIndex == index) {
                    textChar[i] = changeChar;
                }
            } else {
                wordIndex = -1;
            }
            wordIndex++;
        }
        return String.copyValueOf(textChar);
    }

    @Override
    public String changeSubsequentLetter(String text, char precedLetter, char incorrectLetter,
                                         char correctLetter) throws ProjectException {
        if (text == null) {
            throw new ProjectException("Invalid parameters");
        }

        char[] textChar = text.toCharArray();
        char[] precedLetterCases = getCasesOfLetter(precedLetter);
        char[] incorrectLetterCases = getCasesOfLetter(incorrectLetter);

        for (int i = 1; i < textChar.length; i++) {
            if ((textChar[i - 1] == precedLetterCases[0] || textChar[i - 1] == precedLetterCases[1]) &&
                    (textChar[i] == incorrectLetterCases[0] || textChar[i] == incorrectLetterCases[1])) {

                textChar[i] = correctLetter;
                i++;
            }
        }
        return String.copyValueOf(textChar);
    }

    @Override
    public String changeWord(String text, int length, int startSubstring,
                             int endSubstring) throws ProjectException {

        if (!isValidData(text, length, startSubstring, endSubstring)) {
            throw new ProjectException("Invalid parameters");
        }

        String substring = text.substring(startSubstring, endSubstring);

        char[] textChar = text.toCharArray();
        ArrayList<Character> word = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < textChar.length; i++) {
            while (Character.isLetterOrDigit(textChar[i])) {
                word.add(textChar[i]);
                i++;
            }
            if ((word.size() != length)) {
                result.append(listToString(word));
            } else {
                result.append(substring);
            }
            result.append(textChar[i]);
            word.clear();
        }
        return result.toString();
    }
}
