package by.epam.basavets.bean;

import java.util.List;

public class Sentence {
    private String sentence;
    private List<Word> wordList;

    public Sentence(String sentence) {
        this.sentence = sentence;
    }

    public Sentence() {
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public List<Word> getWordList() {
        return wordList;
    }

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
    }
}
