package by.epam.basavets.servise;

import junit.framework.TestCase;

import java.io.IOException;

public class TextWithLongSentenceTest extends TestCase {

    String PATH_FILE = "src/main/resources/testText.txt";
    String textLongSentences = "Заячьи лапы - рассказ Константина Паустовского, который не оставит равнодушным ни одного читателя, " +
            "он начинается с того, что мальчик приносит к фельдшеру обгоревшего зайца, " +
            "а тот прогоняет его на что готов мальчик и его.";
    ReadText readText = new ReadText();


    public void testGetTextWithLongSentences() throws IOException {
        String expected = textLongSentences;
        String actual = readText.getTextWithLongSentences(PATH_FILE).getParent();
        assertEquals(expected.trim(), actual.trim());
    }
}
