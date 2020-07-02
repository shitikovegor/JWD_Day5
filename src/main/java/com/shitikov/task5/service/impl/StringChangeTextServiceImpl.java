package com.shitikov.task5.service.impl;

import com.shitikov.task5.exception.ProjectException;
import com.shitikov.task5.service.ChangeTextService;
import com.shitikov.task5.validator.DataValidator;

public class StringChangeTextServiceImpl implements ChangeTextService {

    private static final String PUNCT_REGEX = "\\s*\\p{Punct}*\\s+";
    private static final String SPACE = "\\s+";
    private static final String CHANGE_GROUP = "$1%s";
    private static final String LETTER_REGEX = "[a-zA-Zа-яА-ЯёЁ]";

    static char[] getCasesOfLetter(char letter) {
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

    @Override
    public String changeCharacter(String text, int index, char changeChar) throws ProjectException {
        if (text == null || index < 0) {
            throw new ProjectException("Invalid parameters");
        }
        StringBuilder result = new StringBuilder();
        String[] words = text.split(SPACE);
        int length;
        int buildIndex = index;

        for (String word : words) {
            length = result.length();
            result.append(word);
            buildIndex = length + index;

            if (index < word.replaceAll(PUNCT_REGEX, "").length()) {
                if (String.valueOf(word.charAt(index)).matches(LETTER_REGEX)) {
                    result.setCharAt(buildIndex, changeChar);
                }
            }
            result.append(" ");
        }
        return result.toString();
    }

    @Override
    public String changeSubsequentLetter(String text, char precedLetter,
                                         char incorrectLetter, char correctLetter) throws ProjectException {
        if (text == null) {
            throw new ProjectException("Invalid parameters");
        }

        char[] precedLetterCases = getCasesOfLetter(precedLetter);
        char[] incorrectLetterCases = getCasesOfLetter(incorrectLetter);

        String searchRegex = String.format("([%s%s])([%s%s])", precedLetterCases[0], precedLetterCases[1],
                incorrectLetterCases[0], incorrectLetterCases[1]);

        String result = text.replaceAll(searchRegex, String.format(CHANGE_GROUP, "o"));

        return result;
    }

    @Override
    public String changeWord(String text, int length, int startSubstring, int endSubstring) throws ProjectException {
        DataValidator validator = new DataValidator();

        if (!validator.isValidData(text, length, startSubstring, endSubstring)) {
            throw new ProjectException("Invalid parameters");
        }

        StringBuilder result = new StringBuilder(text);
        String changeWord = text.substring(startSubstring, endSubstring);

        String[] words = text.split(PUNCT_REGEX);
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
}
