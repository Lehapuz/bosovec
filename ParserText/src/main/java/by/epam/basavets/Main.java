package by.epam.basavets;

import by.epam.basavets.servise.ReadText;

import java.io.IOException;

public class Main {

    private static final String PATH_FILE = "src/main/resources/text.txt";

    public static void main(String[] args) throws IOException {
        ReadText readText = new ReadText();
        readText.getSortedParagraph(PATH_FILE);
        System.out.println("\n");
        readText.getSentenceWithLongWord(PATH_FILE);
        System.out.println("\n");
        readText.getTextWithLongSentences(PATH_FILE);
        System.out.println("\n");
        readText.getCountRepeatWord(PATH_FILE);
        System.out.println("\n");
        readText.getVowelsCount(PATH_FILE);
    }
}
