package by.epam.basavets.service;

import junit.framework.TestCase;

public class VowelsCountTest extends TestCase {

    String sentenceVowelsCount = "А почему тогда у тебя хвост куцый.";
    TextService textService = new TextService();


    public void testGetVowelsCount() {
        int expected = 12;
        int actual = textService.getVowelsCountSentence(sentenceVowelsCount);
        assertEquals(expected, actual);
    }
}
