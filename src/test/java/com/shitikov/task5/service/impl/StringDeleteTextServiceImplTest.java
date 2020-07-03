package com.shitikov.task5.service.impl;

import com.shitikov.task5.exception.ProjectException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class StringDeleteTextServiceImplTest {
    StringDeleteTextServiceImpl service;

    @BeforeMethod
    public void setUp() {
        service = new StringDeleteTextServiceImpl();
    }

    @Test(expectedExceptions = ProjectException.class)
    public void testDeleteNotLettersException() throws ProjectException {
        service.deleteNotLetters(null);
    }

    @Test(dataProvider = "deleteNotLettersData", dataProviderClass = TextDataProvider.class)
    public void testDeleteNotLettersPositive(String text, String expected) throws ProjectException {
        String actual = service.deleteNotLetters(text);
        assertEquals(actual, expected, "Test failed as... ");
    }

    @Test()
    public void testDeleteNotLettersNegative() throws ProjectException {
        String actual = service.deleteNotLetters("dgjsj@gmail.com");
        String expected = "dgjsj@gmail.com";
        assertNotEquals(actual, expected, "Test failed as... ");
    }

    @DataProvider(name = "wordExceptionData")
    public Object[][] createWordExceptionData() {
        return new Object[][]{{null, 2}, {"Text", -3}, {"Text", 6}};
    }

    @Test(dataProvider = "wordExceptionData", expectedExceptions = ProjectException.class)
    public void testDeleteWordsException(String text, int length) throws ProjectException {
        service.deleteWords(text, length);
    }

    @Test(dataProvider = "deleteWordsData", dataProviderClass = TextDataProvider.class)
    public void testDeleteWordsPositive(String text, String expected) throws ProjectException {
        String actual = service.deleteWords(text, 4);
        assertEquals(actual, expected, "Test failed as... ");
    }

    @Test(dataProvider = "deleteWordsData", dataProviderClass = TextDataProvider.class)
    public void testDeleteWordsNegative(String text, String expected) throws ProjectException {
        String actual = service.deleteWords(text, 6);
        assertNotEquals(actual, expected, "Test failed as... ");
    }
}