package by.epam.basavets;

import by.epam.basavets.service.TextService;

import java.io.IOException;

public class Main {

    private static final String PATH_FILE = "src/main/resources/text.txt";

    public static void main(String[] args) throws IOException {
        TextService textService = new TextService();
        textService.getSortedParagraph(PATH_FILE);
        System.out.println("\n");
        textService.getSentenceWithLongWord(PATH_FILE);
        System.out.println("\n");
        textService.getTextWithLongSentences(PATH_FILE);
        System.out.println("\n");
        textService.getCountRepeatWord(PATH_FILE);
        System.out.println("\n");
        textService.getVowelsCount(PATH_FILE);
    }
}
