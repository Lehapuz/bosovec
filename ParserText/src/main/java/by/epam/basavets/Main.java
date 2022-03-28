package by.epam.basavets;

import by.epam.basavets.servise.ReadText;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ReadText readText = new ReadText();
        readText.getSortedParagraph();
        System.out.println("\n");
        readText.getSentenceWithLongWord();
        System.out.println("\n");
        readText.getTextWithLongSentences();
        System.out.println("\n");
        readText.getCountRepeatWord();
        System.out.println("\n");
        readText.getVowelsCount();
    }
}
