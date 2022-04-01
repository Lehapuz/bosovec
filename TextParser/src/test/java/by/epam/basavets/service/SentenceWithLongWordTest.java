package by.epam.basavets.service;

import by.epam.basavets.bean.Sentence;
import junit.framework.TestCase;

import java.io.IOException;

public class SentenceWithLongWordTest extends TestCase {

    String PATH_FILE = "src/main/resources/testText.txt";
    String longWordSentence = "Год ещё, помню, в пестунах-няньках ходил, а потом сбежа-а-ал";
    TextService textService = new TextService();
    Sentence longWordSent = new Sentence(longWordSentence);


    public void testGetSentenceWithLongWord() throws IOException {
        String expected = longWordSent.getSentence();
        String actual = textService.getSentenceWithLongWord(PATH_FILE).getSentence();
        assertEquals(expected.trim(), actual.trim());
    }
}
