package com.shitikov.task5.validator;

public class DataValidator {

    public boolean isValidData(String text, int length, int startIndex, int finishIndex) {
        if (text == null) {
            return false;
        }
        return (startIndex >= 0 && startIndex < text.length() &&
                finishIndex >= 0 && finishIndex < text.length() &&
                finishIndex >= startIndex && length > 0 && length < text.length());
    }
}
