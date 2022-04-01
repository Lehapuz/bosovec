package by.epam.basavets.service;

import by.epam.basavets.bean.Paragraph;
import by.epam.basavets.bean.Sentence;
import by.epam.basavets.bean.Text;
import by.epam.basavets.bean.Word;
import by.epam.basavets.storage.TextStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TextService {

    private final Text text = new Text();
    private final TextStorage textStorage = new TextStorage(text);
    private final Logger logger = LogManager.getRootLogger();


    private Text readText(String path) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            logger.error("Файл не найден");
        }
        while (true) {
            String line = null;
            if (bufferedReader != null) {
                line = bufferedReader.readLine();
            }
            if (line == null) {
                break;
            } else {
                builder.append(line).append("\n");
            }
        }
        textStorage.getText().setText(builder.toString());
        return text;
    }


    private TextStorage textParser(String path) throws IOException {
        Text text = readText(path);
        List<Paragraph> paragraphList = new ArrayList<>();
        String[] paragraphs = text.getText().split("\n");
        for (int i = 0; i < paragraphs.length; i++) {
            List<Sentence> sentenceList = new ArrayList<>();
            String textParagraph = paragraphs[i];
            Paragraph paragraph = new Paragraph(textParagraph);
            paragraphList.add(paragraph);
            textStorage.setParagraph(paragraph);
            String[] textSentences = textParagraph.split("!|\\?|\\.|…");
            for (int j = 0; j < textSentences.length; j++) {
                List<Word> wordList = new ArrayList<>();
                String textSentence = textSentences[j];
                Sentence sentence = new Sentence(textSentence);
                sentenceList.add(sentence);
                textStorage.setSentence(sentence);
                String[] textWords = textSentence.split("\\s+");
                for (int k = 0; k < textWords.length; k++) {
                    String textWord = textWords[k];
                    String validTextWord = textWord.replaceAll("[,;«»:()„“—]", "");
                    if (!validTextWord.equals("")) {
                        Word word = new Word(validTextWord);
                        wordList.add(word);
                        textStorage.setWord(word);
                    }
                }
                textStorage.getSentence().setChildrenList(wordList);
            }
            textStorage.getParagraph().setChildrenList(sentenceList);
        }
        textStorage.getText().setChildrenList(paragraphList);

        return textStorage;
    }


    public List<Paragraph> getSortedParagraph(String path) throws IOException {
        TextStorage storage = textParser(path);

        storage.getText().getChildrenList().stream().sorted(Paragraph::compareTo)
                .collect(Collectors.toList()).forEach(paragraph -> paragraph.getChildrenList()
                .forEach(sentence -> System.out.println(sentence.getSentence())));

        return storage.getText().getChildrenList().stream().sorted(Paragraph::compareTo)
                .collect(Collectors.toList());
    }


    public Sentence getSentenceWithLongWord(String path) throws IOException {
        Sentence maxLongWordSentence = new Sentence();
        String[] longWords = setBuilderWord(path).split("\n");
        String[] longWordSentences = setBuilderSentence(path).split("\n");

        for (int i = 0; i < longWordSentences.length; i++) {
            String longWordSentence = longWordSentences[i];
            if (longWordSentence.contains(maxLengthWord(longWords))) {
                maxLongWordSentence.setSentence(longWordSentence);
            }
        }
        System.out.println(maxLongWordSentence.getSentence());
        return maxLongWordSentence;
    }

    private String setBuilderWord(String path) {
        StringBuilder builderWord = new StringBuilder();
        try {
            TextStorage storage = textParser(path);

            storage.getText().getChildrenList().forEach(paragraph -> paragraph.getChildrenList()
                    .forEach(sentence -> builderWord.append(sentence.getChildrenList().stream()
                            .max(Comparator.comparing(word -> word.getWord().length())).get().getWord() + "\n")
                    ));
        } catch (Exception e) {
            logger.error("Элемент не найден");
        }
        return builderWord.toString();
    }

    private String setBuilderSentence(String path) throws IOException {
        StringBuilder builderSentence = new StringBuilder();
        TextStorage storage = textParser(path);
        storage.getText().getChildrenList().forEach(paragraph -> paragraph.getChildrenList()
                .forEach(sentence -> builderSentence.append(sentence.getSentence() + "\n")
                ));
        return builderSentence.toString();
    }

    private String maxLengthWord(String[] words) {
        String maxLengthWord = "";
        for (int i = 0; i < words.length; i++) {
            String longWord = words[i];
            for (int j = 0; j < words.length; j++) {
                String longWord1 = words[j];
                if (longWord1.length() > longWord.length()) {
                    maxLengthWord = longWord1;
                }
            }
        }
        return maxLengthWord;
    }


    public Text getTextWithLongSentences(String path) throws IOException {
        TextStorage storage = textParser(path);
        StringBuilder stringBuilder = new StringBuilder();
        int minLength = 30;

        storage.getText().getChildrenList().forEach(paragraph -> paragraph.getChildrenList()
                .stream().filter(sentence -> sentence.getChildrenList().size() > minLength)
                .collect(Collectors.toList()).forEach(sentence ->
                        stringBuilder.append(sentence.getSentence() + "." + "\n")));

        text.setText(stringBuilder.toString());
        System.out.println(text.getText());

        return text;
    }


    public int getCountRepeatWord(String path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        TextStorage storage = textParser(path);

        storage.getText().getChildrenList().forEach(paragraph -> paragraph.getChildrenList()
                .forEach(sentence -> sentence.getChildrenList().forEach(word -> stringBuilder.append(word.getWord() + "\n")
                )));

        String[] words = stringBuilder.toString().toLowerCase().split("\n");
        Map<String, Integer> wordMap = new HashMap<>();

        for (String word : words) {
            int x = wordMap.getOrDefault(word, 0) + 1;
            wordMap.put(word.toLowerCase(), x);
            if (wordMap.get(word) > 1) {
                System.out.println("Слово - " + word + " Количество повторений - " + wordMap.get(word));
            }
        }
        System.out.println("Количество повторяющихся слов - " + (words.length - wordMap.size()));
        return words.length - wordMap.size();
    }


    public void getVowelsCount(String path) throws IOException {
        TextStorage storage = textParser(path);
        StringBuilder stringBuilder = new StringBuilder();

        storage.getText().getChildrenList().forEach(paragraph -> paragraph.getChildrenList()
                .forEach(sentence -> stringBuilder.append(sentence.getSentence() + "\n")));

        String text = stringBuilder.toString().replaceAll("[^А-я\n]", "");

        String[] sentences = text.split("\n");
        for (int i = 0; i < sentences.length; i++) {
            String sentence = sentences[i];
            getVowelsCountSentence(sentence);
        }
    }

    public int getVowelsCountSentence(String sentence) {
        List<Character> vowels = Arrays.asList('а', 'е', 'и', 'о', 'у', 'ы', 'э', 'ю', 'я');
        int countVowels = 0;
        int countConsonants = 0;
        for (int j = 0; j < sentence.length(); j++) {
            if (vowels.contains(sentence.toLowerCase().charAt(j))) {
                countVowels += 1;
            } else {
                countConsonants += 1;
            }
        }
        System.out.println("Количество гласных в предложении - " + countVowels
                + " Количество согласных в предложеии - " + countConsonants);
        return countVowels;
    }
}
