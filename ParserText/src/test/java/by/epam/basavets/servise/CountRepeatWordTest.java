package by.epam.basavets.servise;

import junit.framework.TestCase;

import java.io.IOException;

public class CountRepeatWordTest extends TestCase {

    String PATH_FILE1 = "src/main/resources/testText1.txt";
    ReadText readText = new ReadText();


    public void testGetCountRepeatWord() throws IOException {
        int expected = 4;
        int actual = readText.getCountRepeatWord(PATH_FILE1);
        assertEquals(expected, actual);
    }
}
