package by.epam.basavets.servise;

import by.epam.basavets.bean.Sentence;
import junit.framework.TestCase;

import java.io.IOException;

public class SentenceWithLongWordTest extends TestCase {

    String PATH_FILE = "src/main/resources/testText.txt";
    String longWordSentence = "Год ещё, помню, в пестунах-няньках ходил, а потом сбежа-а-ал";
    ReadText readText = new ReadText();
    Sentence longWordSent = new Sentence(longWordSentence);


    public void testGetSentenceWithLongWord() throws IOException {
        String expected = longWordSent.getParent();
        String actual = readText.getSentenceWithLongWord(PATH_FILE).getParent();
        assertEquals(expected.trim(), actual.trim());
    }
}
