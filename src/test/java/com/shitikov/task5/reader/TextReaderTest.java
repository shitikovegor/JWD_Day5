package com.shitikov.task5.reader;

import com.shitikov.task5.exception.ProjectException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.testng.Assert.*;

public class TextReaderTest {
    TextReader reader;

    @BeforeMethod
    public void setUp() {
        reader = new TextReader();
    }

    @Test(expectedExceptions = ProjectException.class)
    public void testReadFileException() throws ProjectException {
        reader.readFile("input/exception_test_file.txt");
    }

    @Test
    public void testReadFilePositive() throws ProjectException {
        String actual = reader.readFile("input/test_file.txt");
        String expected = "In addition to complications, Unicode also brings new possibilities. One is that each " +
                "Unicode character belongs to a certain category.";
        assertEquals(actual, expected, "Test failed as... ");
    }

    @Test
    public void testReadFileNegative() throws ProjectException {
        String actual = reader.readFile("input/test_file.txt");
        String expected = "2 5 1 3 12";

        assertNotEquals(actual, expected, "Test failed as... ");
    }

    @Test
    public void testReadConsolePositive() {
        try {
            String expected = "This is test input!";

            InputStream input = new ByteArrayInputStream(expected.getBytes());
            System.setIn(input);
            String actual = reader.readConsole();
            assertEquals(actual, expected, "Test failed as... ");
        } catch (ProjectException e) {
            fail("Exception has occurred.");
        }
    }

    @Test
    public void testReadConsoleNegative() {
        try {
            String consoleInput = "This is test input!";
            InputStream input = new ByteArrayInputStream(consoleInput.getBytes());
            System.setIn(input);
            String actual = reader.readConsole();
            String expected = "This is not test input!";

            assertNotEquals(actual, expected, "Test failed as... ");
        } catch (ProjectException e) {
            fail("Exception has occurred.");
        }
    }
}