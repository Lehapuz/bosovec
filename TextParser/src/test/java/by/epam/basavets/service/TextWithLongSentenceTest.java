package by.epam.basavets.service;

import junit.framework.TestCase;

import java.io.IOException;

public class TextWithLongSentenceTest extends TestCase {

    String PATH_FILE = "src/main/resources/testText.txt";
    String textLongSentences = "Заячьи лапы - рассказ Константина Паустовского, который не оставит равнодушным ни одного читателя, " +
            "он начинается с того, что мальчик приносит к фельдшеру обгоревшего зайца, " +
            "а тот прогоняет его на что готов мальчик и его.";
    TextService textService = new TextService();


    public void testGetTextWithLongSentences() throws IOException {
        String expected = textLongSentences;
        String actual = textService.getTextWithLongSentences(PATH_FILE).getText();
        assertEquals(expected.trim(), actual.trim());
    }
}
