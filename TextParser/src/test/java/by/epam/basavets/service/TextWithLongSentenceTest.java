package by.epam.basavets.service;

import junit.framework.TestCase;

import java.io.IOException;

public class TextWithLongSentenceTest extends TestCase {

    String PATH_FILE = "src/main/resources/testText.txt";
    String textLongSentences = "������ ���� - ������� ����������� ������������, ������� �� ������� ����������� �� ������ ��������, " +
            "�� ���������� � ����, ��� ������� �������� � ��������� ����������� �����, " +
            "� ��� ��������� ��� �� ��� ����� ������� � ���.";
    TextService textService = new TextService();


    public void testGetTextWithLongSentences() throws IOException {
        String expected = textLongSentences;
        String actual = textService.getTextWithLongSentences(PATH_FILE).getText();
        assertEquals(expected.trim(), actual.trim());
    }
}
