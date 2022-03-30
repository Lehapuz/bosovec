package by.epam.basavets.servise;

import junit.framework.TestCase;

public class VowelsCountTest extends TestCase {

    String sentenceVowelsCount = "� ������ ����� � ���� ����� �����.";
    ReadText readText = new ReadText();


    public void testGetVowelsCount() {
        int expected = 12;
        int actual = readText.getVowelsCountSentence(sentenceVowelsCount);
        assertEquals(expected, actual);
    }
}
