package com.shitikov.task5.service;

import com.shitikov.task5.exception.ProjectException;

public interface ChangeTextService {
    String changeCharacter(String text, int index, char changeChar) throws ProjectException;

    String changeSubsequentLetter(String text, char precedLetter,
                                  char incorrectLetter, char correctLetter)  throws ProjectException;

    String changeWord(String text, int length, int startSubstring, int endSubstring)  throws ProjectException;
}
