package by.epam.basavets.servise;

import junit.framework.TestCase;

import java.io.IOException;

public class TextWithLongSentenceTest extends TestCase {

    String PATH_FILE = "src/main/resources/testText.txt";
    String textLongSentences = "������ ���� - ������� ����������� ������������, ������� �� ������� ����������� �� ������ ��������, " +
            "�� ���������� � ����, ��� ������� �������� � ��������� ����������� �����, " +
            "� ��� ��������� ��� �� ��� ����� ������� � ���.";
    ReadText readText = new ReadText();


    public void testGetTextWithLongSentences() throws IOException {
        String expected = textLongSentences;
        String actual = readText.getTextWithLongSentences(PATH_FILE).getParent();
        assertEquals(expected.trim(), actual.trim());
    }
}
