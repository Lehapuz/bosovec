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
    private final Text text = new Text();
    private final Storage storage = new Storage(text);


    private Text readText(String path) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            } else {
                builder.append(line).append("\n");
            }
        }
        storage.getText().setParent(builder.toString());
        return text;
    }


    private Storage getStorage(String path) throws IOException {
        Text text = readText(path);

        List<Paragraph> paragraphList = new ArrayList<>();
        String[] paragraphs = text.getParent().split("\n");
        for (int i = 0; i < paragraphs.length; i++) {
            List<Sentence> sentenceList = new ArrayList<>();
            String textParagraph = paragraphs[i];
            Paragraph paragraph = new Paragraph(textParagraph);
            paragraphList.add(paragraph);
            storage.setParagraph(paragraph);
            String[] textSentences = textParagraph.split("!|\\?|\\.|…");
            for (int j = 0; j < textSentences.length; j++) {
                List<Word> wordList = new ArrayList<>();
                String textSentence = textSentences[j];
                Sentence sentence = new Sentence(textSentence);
                sentenceList.add(sentence);
                storage.setSentence(sentence);
                String[] textWords = textSentence.split("\\s+");
                for (int k = 0; k < textWords.length; k++) {
                    String textWord = textWords[k];
                    String validTextWord = textWord.replaceAll("[,;«»:()„“—]", "");
                    if (!validTextWord.equals("")) {
                        Word word = new Word(validTextWord);
                        wordList.add(word);
                        storage.setWord(word);
                    }
                }
                storage.getSentence().setChildrenList(wordList);
            }
            storage.getParagraph().setChildrenList(sentenceList);
        }
        storage.getText().setChildrenList(paragraphList);
        return storage;
    }


    public List<Paragraph> getSortedParagraph(String path) throws IOException {
        Storage storage = getStorage(path);

        storage.getText().getChildrenList().stream().sorted(Paragraph::compareTo)
                .collect(Collectors.toList()).forEach(paragraph -> paragraph.getChildrenList()
                .forEach(sentence -> System.out.println(sentence.getParent())));

        return storage.getText().getChildrenList().stream().sorted(Paragraph::compareTo)
                .collect(Collectors.toList());
    }


    public Sentence getSentenceWithLongWord(String path) throws IOException {
        Storage storage = getStorage(path);
        StringBuilder builderWord = new StringBuilder();
        StringBuilder builderSentence = new StringBuilder();
        Sentence maxLongWordSentence = new Sentence();

        storage.getText().getChildrenList().forEach(paragraph -> paragraph.getChildrenList()
                .forEach(sentence -> builderWord.append(sentence.getChildrenList().stream()
                        .max(Comparator.comparing(word -> word.getWord().length())).get().getWord() + "\n")
                ));

        String[] longWords = builderWord.toString().split("\n");

        storage.getText().getChildrenList().forEach(paragraph -> paragraph.getChildrenList()
                .forEach(sentence -> builderSentence.append(sentence.getParent() + "\n")
                ));

        String[] longWordSentences = builderSentence.toString().split("\n");

        for (int i = 0; i < longWordSentences.length; i++) {
            String longWordSentence = longWordSentences[i];
            if (longWordSentence.contains(maxLengthWord(longWords))) {
                maxLongWordSentence.setParent(longWordSentence);
            }
        }
        System.out.println(maxLongWordSentence.getParent());
        return maxLongWordSentence;
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
        Storage storage = getStorage(path);
        StringBuilder stringBuilder = new StringBuilder();
        int minLength = 30;

        storage.getText().getChildrenList().forEach(paragraph -> paragraph.getChildrenList()
                .stream().filter(sentence -> sentence.getChildrenList().size() > minLength)
                .collect(Collectors.toList()).forEach(sentence ->
                        stringBuilder.append(sentence.getParent() + "." + "\n")));

        text.setParent(stringBuilder.toString());
        System.out.println(text.getParent());

        return text;
    }


    public int getCountRepeatWord(String path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Storage storage = getStorage(path);

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
        Storage storage = getStorage(path);
        StringBuilder stringBuilder = new StringBuilder();

        storage.getText().getChildrenList().forEach(paragraph -> paragraph.getChildrenList()
                .forEach(sentence -> stringBuilder.append(sentence.getParent() + "\n")));

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
