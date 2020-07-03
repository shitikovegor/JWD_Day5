package com.shitikov.task5.service.impl;

import com.shitikov.task5.exception.ProjectException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CharChangeTextServiceImplTest {
    CharChangeTextServiceImpl service;

    @BeforeMethod
    public void setUp() {
        service = new CharChangeTextServiceImpl();
    }

    @DataProvider(name = "charExceptionData")
    public Object[][] createCharExceptionData() {
        return new Object[][]{{null, 2}, {"Text", -3}};
    }

    @Test(dataProvider = "charExceptionData", expectedExceptions = ProjectException.class)
    public void testChangeCharacterException(String text, int index) throws ProjectException {
        service.changeCharacter(text, index, 'c');
    }

    @Test(dataProvider = "changeCharacterData", dataProviderClass = TextDataProvider.class)
    public void testChangeCharacterPositive(String text, String expected) throws ProjectException {
        String actual = service.changeCharacter(text, 4, 'T');
        assertEquals(actual, expected, "Test failed as... ");
    }

    @Test(dataProvider = "changeCharacterData", dataProviderClass = TextDataProvider.class)
    public void testChangeCharacterNegative(String text, String expected) throws ProjectException {
        String actual = service.changeCharacter(text, 3, 'B');
        assertNotEquals(actual, expected, "Test failed as... ");
    }

    @Test(expectedExceptions = ProjectException.class)
    public void testChangeSubsequentLetterException() throws ProjectException {
        service.changeSubsequentLetter(null, 'p', 'o', 'a');
    }

    @Test(dataProvider = "changeSubsequentLetterData", dataProviderClass = TextDataProvider.class)
    public void testChangeSubsequentLetterPositive(String text, String expected) throws ProjectException {
        String actual = service.changeSubsequentLetter(text, 'р', 'а', 'о');
        assertEquals(actual, expected, "Test failed as... ");
    }

    @Test(dataProvider = "changeSubsequentLetterData", dataProviderClass = TextDataProvider.class)
    public void testChangeSubsequentLetterNegative(String text, String expected) throws ProjectException {
        String actual = service.changeSubsequentLetter(text, 'h', 'а', 'о');
        assertNotEquals(actual, expected, "Test failed as... ");
    }

    @DataProvider(name = "wordExceptionData")
    public Object[][] createWordExceptionData() {
        return new Object[][]{{null, 2, 0, 3}, {"Text", -3, 2, 5}, {"Text", 0, 2, 5},
                {"Text", 4, -1, 2}, {"Text", 4, 0, -2}, {"Text", 4, 6, 2}};
    }

    @Test(dataProvider = "wordExceptionData", expectedExceptions = ProjectException.class)
    public void testChangeWordException(String text, int length, int startSubstring,
                                        int endSubstring) throws ProjectException {
        service.changeWord(text, length, startSubstring, endSubstring);
    }

    @Test(dataProvider = "changeWordData", dataProviderClass = TextDataProvider.class)
    public void testChangeWordPositive(String text, String expected) throws ProjectException {
        String actual = service.changeWord(text, 5, 0, 4);
        assertEquals(actual, expected, "Test failed as... ");
    }

    @Test(dataProvider = "changeWordData", dataProviderClass = TextDataProvider.class)
    public void testChangeWordNegative(String text, String expected) throws ProjectException {
        String actual = service.changeWord(text, 4, 1, 3);
        assertNotEquals(actual, expected, "Test failed as... ");
    }
}