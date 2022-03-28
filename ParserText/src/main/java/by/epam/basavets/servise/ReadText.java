package by.epam.basavets.servise;

import by.epam.basavets.bean.Paragraph;
import by.epam.basavets.bean.Sentence;
import by.epam.basavets.bean.Text;
import by.epam.basavets.bean.Word;
import by.epam.basavets.dao.Storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ReadText {
    private final String FILE_PATH = "src/main/resources/text.txt";
    private final Text text = new Text();
    private final Storage storage = new Storage(text);


    private Text readText() throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH));
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            } else {
                builder.append(line).append("\n");
            }
        }
        storage.getText().setText(builder.toString());
        return text;
    }


    private Storage getStorage() throws IOException {
        Text text = readText();
        List<Paragraph> paragraphList = new ArrayList<>();
        String[] paragraphs = text.getText().split("\n");
        for (int i = 0; i < paragraphs.length; i++) {
            List<Sentence> sentenceList = new ArrayList<>();
            String textParagraph = paragraphs[i];
            Paragraph paragraph = new Paragraph(textParagraph);
            paragraphList.add(paragraph);
            storage.setParagraph(paragraph);
            String[] textSentences = textParagraph.split("!|\\?|\\.|…");
            for (int j = 0; j < textSentences.length; j++) {
                String textSentence = textSentences[j];
                Sentence sentence = new Sentence(textSentence);
                sentenceList.add(sentence);
                storage.setSentence(sentence);
                String[] textWords = textSentence.split("\\s+");
                List<Word> wordList = new ArrayList<>();
                for (int k = 0; k < textWords.length; k++) {
                    String textWord = textWords[k];
                    String validTextWord = textWord.replaceAll("[,;«»:()„“—]", "");
                    if (!validTextWord.equals("")) {
                        Word word = new Word(validTextWord);
                        wordList.add(word);
                        storage.setWord(word);
                    }
                }
                storage.getSentence().setWordList(wordList);
            }
            storage.getParagraph().setSentenceList(sentenceList);
        }
        storage.getText().setParagraphList(paragraphList);
        return storage;
    }


    public List<Paragraph> getSortedParagraph() throws IOException {
        Storage storage = getStorage();

        storage.getText().getParagraphList().stream().sorted(Paragraph::compareTo)
                .collect(Collectors.toList()).forEach(paragraph -> paragraph.getSentenceList()
                .forEach(sentence -> System.out.println(sentence.getSentence())));

        return storage.getText().getParagraphList().stream().sorted(Paragraph::compareTo)
                .collect(Collectors.toList());
    }


    public Sentence getSentenceWithLongWord() throws IOException {
        Storage storage = getStorage();
        Sentence sentence = new Sentence();
        Optional<Word> word = storage.getSentence().getWordList().stream().max(Comparator
                .comparing(longWord -> longWord.getWord().length()));
        if (storage.getSentence().getWordList().contains(word.get())) {
            sentence = storage.getSentence();
        }
        System.out.println(sentence.getSentence());
        return sentence;
    }


    public Text getTextWithLongSentences() throws IOException {
        Storage storage = getStorage();
        StringBuilder stringBuilder = new StringBuilder();

        storage.getText().getParagraphList().forEach(paragraph -> paragraph.getSentenceList()
                .stream().filter(sentence -> sentence.getWordList().size() > 30)
                .collect(Collectors.toList()).forEach(sentence ->
                        stringBuilder.append(sentence.getSentence() + "." + "\n")));

        text.setText(stringBuilder.toString());
        System.out.println(text.getText());

        return text;
    }


    public int getCountRepeatWord() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Storage storage = getStorage();

        storage.getText().getParagraphList().forEach(paragraph -> paragraph.getSentenceList()
                .forEach(sentence -> sentence.getWordList().forEach(word -> stringBuilder.append(word.getWord() + "\n")
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
        System.out.println("Количество повторений - " + (words.length - wordMap.size()));
        return words.length - wordMap.size();
    }


    public void getVowelsCount() throws IOException {
        List<Character> vowels = Arrays.asList('а', 'е', 'и', 'о', 'у', 'ы', 'э', 'ю', 'я');
        Storage storage = getStorage();
        StringBuilder stringBuilder = new StringBuilder();

        storage.getText().getParagraphList().forEach(paragraph -> paragraph.getSentenceList()
                .forEach(sentence -> stringBuilder.append(sentence.getSentence() + "\n")));

        String text = stringBuilder.toString().replaceAll("[^А-я\n]", "");

        String[] sentences = text.split("\n");
        for (int i = 0; i < sentences.length; i++) {
            int countVowels = 0;
            int countConsonants = 0;
            String sentence = sentences[i];
            for (int j = 0; j < sentence.length(); j++) {
                if (vowels.contains(sentence.charAt(j))) {
                    countVowels += 1;
                } else {
                    countConsonants += 1;
                }
            }
            System.out.println("Количество гласных в предложении " + i + " - " + countVowels
                    + " Количество согласных в предложеии - " + countConsonants);
        }
    }
}
