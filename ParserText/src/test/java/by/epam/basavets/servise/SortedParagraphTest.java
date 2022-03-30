package by.epam.basavets.servise;

import by.epam.basavets.bean.Paragraph;
import by.epam.basavets.bean.Text;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SortedParagraphTest extends TestCase {

    String PATH_FILE = "src/main/resources/testText.txt";

    String story = "\t" + "А почему тогда у тебя хвост куцый. " +
            "Отвечай, сколько тебе годов?\n" +
            "\t" + "Не сердись, дяденька Медведь. " +
            "Годов мне ещё ни одного. " +
            "И с полгода не наберётся. " +
            "Да только вы, медведи, живёте шестьдесят лет, а мы, белки, от силы десять. " +
            "И выходит, что мне, полугодке, на ваш медвежий счёт ровно три года! " +
            "Вспомни-ка, Медведь, себя в три годочка. Небось тоже от медведицы стрекача задал?\n" +
            "\t" + "Что правда, то правда - проворчал Медведь. " +
            "Год ещё, помню, в пестунах-няньках ходил, а потом сбежа-а-ал.\n" +
            "\t" + "Да на радостях, помню, улей разворотил. Ох и покатались же на мне пчёлы тогда - посейчас бока чешутся!\n" +
            "\t" + "Заячьи лапы - рассказ Константина Паустовского, который не оставит равнодушным ни одного читателя, " +
            "он начинается с того, что мальчик приносит к фельдшеру обгоревшего зайца, " +
            "а тот прогоняет его на что готов мальчик и его.\n";

    String storyParagraph1 = "\t" + "А почему тогда у тебя хвост куцый. " +
            "Отвечай, сколько тебе годов?";

    String storyParagraph2 = "\t" + "Не сердись, дяденька Медведь. " +
            "Годов мне ещё ни одного. " +
            "И с полгода не наберётся. " +
            "Да только вы, медведи, живёте шестьдесят лет, а мы, белки, от силы десять. " +
            "И выходит, что мне, полугодке, на ваш медвежий счёт ровно три года! " +
            "Вспомни-ка, Медведь, себя в три годочка. Небось тоже от медведицы стрекача задал?";

    String storyParagraph3 = "\t" + "Что правда, то правда - проворчал Медведь. " +
            "Год ещё, помню, в пестунах-няньках ходил, а потом сбежа-а-ал. " +
            "Да на радостях, помню, улей разворотил. Ох и покатались же на мне пчёлы тогда - посейчас бока чешутся!";

    String storyParagraph4 = "\t" + "Заячьи лапы - рассказ Константина Паустовского, который не оставит равнодушным ни одного читателя, " +
            "он начинается с того, что мальчик приносит к фельдшеру обгоревшего зайца, " +
            "а тот прогоняет его на что готов мальчик и его.";

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
