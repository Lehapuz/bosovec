package by.epam.basavets.service;

import junit.framework.TestCase;

import java.io.IOException;

public class CountRepeatWordTest extends TestCase {

    String PATH_FILE1 = "src/main/resources/testText1.txt";
    TextService textService = new TextService();


    public void testGetCountRepeatWord() throws IOException {
        int expected = 4;
        int actual = textService.getCountRepeatWord(PATH_FILE1);
        assertEquals(expected, actual);
    }
}
