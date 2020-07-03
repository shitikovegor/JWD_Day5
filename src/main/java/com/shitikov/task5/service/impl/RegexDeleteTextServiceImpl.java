package com.shitikov.task5.service.impl;

import com.shitikov.task5.exception.ProjectException;
import com.shitikov.task5.service.DeleteTextService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDeleteTextServiceImpl extends TextService implements DeleteTextService {

    @Override
    public String deleteNotLetters(String text) throws ProjectException {
        if (text == null) {
            throw new ProjectException("Invalid parameters");
        }

        Pattern pattern = Pattern.compile(SYMBOL_REGEX);
        Matcher matcher = pattern.matcher(text);
        String result = matcher.replaceAll(" ");

        return result;
    }

    @Override
    public String deleteWords(String text, int length) throws ProjectException {
        if (text == null || length < 0 || length > text.length()) {
            throw new ProjectException("Invalid parameters");
        }

        String regex = "\\b(?i)(?=[^aeiouаоуыэяёюиеАОУЫЭЯЁЮИЕ])\\p{L}\\p{L}{%d}\\b";
        Pattern pattern = Pattern.compile(String.format(regex, length - 1));
        Matcher matcher = pattern.matcher(text);

        String result = matcher.replaceAll("");

        return result;
    }
}
