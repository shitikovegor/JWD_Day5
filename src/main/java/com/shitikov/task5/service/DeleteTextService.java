package com.shitikov.task5.service;

import com.shitikov.task5.exception.ProjectException;

public interface DeleteTextService {
    String deleteNotLetters(String text) throws ProjectException;

    String deleteWords(String text, int length) throws ProjectException;
}
