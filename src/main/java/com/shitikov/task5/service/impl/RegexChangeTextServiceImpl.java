package com.shitikov.task5.service.impl;

import com.shitikov.task5.exception.ProjectException;
import com.shitikov.task5.service.ChangeTextService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChangeTextServiceImpl extends TextService implements ChangeTextService {
    private static final String SEARCH_CHAR_REGEX = "\\b(\\w{%d})(\\p{Alpha})(\\w*)";
    private static final String CHANGE_CHAR_REGEX = "$1%s$3";
    private static final String WORD_LENGTH_REGEX = "\\b\\p{L}{%d}\\b";


    @Override
    public String changeCharacter(String text, int index, char changeChar) throws ProjectException {
        if (text == null || index < 0) {
            throw new ProjectException("Invalid parameters");
        }

        Pattern pattern = Pattern.compile(String.format(SEARCH_CHAR_REGEX, index));
        Matcher matcher = pattern.matcher(text);
        String result = matcher.replaceAll(String.format(CHANGE_CHAR_REGEX, changeChar));

        return result;
    }

    @Override
    public String changeSubsequentLetter(String text, char precedLetter, char incorrectLetter, char correctLetter) throws ProjectException {
        if (text == null) {
            throw new ProjectException("Invalid parameters");
        }
        int start = 0;
        int finish = 0;

        char[] precedLetterCases = getCasesOfLetter(precedLetter);
        char[] incorrectLetterCases = getCasesOfLetter(incorrectLetter);

        String searchRegex = String.format(CHANGE_SYMBOL_REGEX, precedLetterCases[0], precedLetterCases[1],
                incorrectLetterCases[0], incorrectLetterCases[1]);

        Pattern pattern = Pattern.compile(searchRegex);
        Matcher matcher = pattern.matcher(text);
        String result = matcher.replaceAll(String.format(CHANGE_GROUP, correctLetter));

        return result;
    }

    @Override
    public String changeWord(String text, int length, int startSubstring, int endSubstring) throws ProjectException {
        if (!isValidData(text, length, startSubstring, endSubstring)) {
            throw new ProjectException("Invalid parameters");
        }

        String changeWord = text.substring(startSubstring, endSubstring);

        Pattern pattern = Pattern.compile(String.format(WORD_LENGTH_REGEX, length));
        Matcher matcher = pattern.matcher(text);

        String result = matcher.replaceAll(changeWord);

        return result;
    }
}
