package by.epam.basavets.servise;

import by.epam.basavets.bean.Paragraph;
import by.epam.basavets.bean.Text;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SortedParagraphTest extends TestCase {

    String PATH_FILE = "src/main/resources/testText.txt";

    String story = "\t" + "� ������ ����� � ���� ����� �����. " +
            "�������, ������� ���� �����?\n" +
            "\t" + "�� �������, �������� �������. " +
            "����� ��� ��� �� ������. " +
            "� � ������� �� ��������. " +
            "�� ������ ��, �������, ����� ���������� ���, � ��, �����, �� ���� ������. " +
            "� �������, ��� ���, ���������, �� ��� �������� ���� ����� ��� ����! " +
            "�������-��, �������, ���� � ��� �������. ������ ���� �� ��������� �������� �����?\n" +
            "\t" + "��� ������, �� ������ - ��������� �������. " +
            "��� ���, �����, � ��������-������� �����, � ����� �����-�-��.\n" +
            "\t" + "�� �� ��������, �����, ���� ����������. �� � ���������� �� �� ��� ����� ����� - �������� ���� �������!\n" +
            "\t" + "������ ���� - ������� ����������� ������������, ������� �� ������� ����������� �� ������ ��������, " +
            "�� ���������� � ����, ��� ������� �������� � ��������� ����������� �����, " +
            "� ��� ��������� ��� �� ��� ����� ������� � ���.\n";

    String storyParagraph1 = "\t" + "� ������ ����� � ���� ����� �����. " +
            "�������, ������� ���� �����?";

    String storyParagraph2 = "\t" + "�� �������, �������� �������. " +
            "����� ��� ��� �� ������. " +
            "� � ������� �� ��������. " +
            "�� ������ ��, �������, ����� ���������� ���, � ��, �����, �� ���� ������. " +
            "� �������, ��� ���, ���������, �� ��� �������� ���� ����� ��� ����! " +
            "�������-��, �������, ���� � ��� �������. ������ ���� �� ��������� �������� �����?";

    String storyParagraph3 = "\t" + "��� ������, �� ������ - ��������� �������. " +
            "��� ���, �����, � ��������-������� �����, � ����� �����-�-��. " +
            "�� �� ��������, �����, ���� ����������. �� � ���������� �� �� ��� ����� ����� - �������� ���� �������!";

    String storyParagraph4 = "\t" + "������ ���� - ������� ����������� ������������, ������� �� ������� ����������� �� ������ ��������, " +
            "�� ���������� � ����, ��� ������� �������� � ��������� ����������� �����, " +
            "� ��� ��������� ��� �� ��� ����� ������� � ���.";

    ReadText readText = new ReadText();
    Text text = new Text(story);
    Paragraph paragraph1 = new Paragraph(storyParagraph1);
    Paragraph paragraph2 = new Paragraph(storyParagraph3);
    Paragraph paragraph3 = new Paragraph(storyParagraph2);
    Paragraph paragraph4 = new Paragraph(storyParagraph4);

    List<Paragraph> paragraphList = new ArrayList<>();

    protected void setUp() {
        paragraphList.add(paragraph4);
        paragraphList.add(paragraph1);
        paragraphList.add(paragraph2);
        paragraphList.add(paragraph3);
        text.setChildrenList(paragraphList);
    }

    public void testGetSortedParagraph() throws IOException {
        StringBuilder expected = new StringBuilder();
        StringBuilder actual = new StringBuilder();
        text.getChildrenList().forEach(paragraph -> expected.append(paragraph.getParent().trim() + "\n"));
        readText.getSortedParagraph(PATH_FILE).forEach(paragraph -> actual.append(paragraph.getParent().trim() + "\n"));
        assertEquals(expected.toString(), actual.toString());
    }
}
