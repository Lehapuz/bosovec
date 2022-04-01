package by.epam.basavets.bean;

import java.util.List;

public class Sentence implements Node<Word> {
    private String sentence;
    private List<Word> wordList;

    public Sentence(String sentence) {
        this.sentence = sentence;
    }

    public Sentence() {
    }

    @Override
    public List<Word> getChildrenList() {
        return wordList;
    }

    @Override
    public void setChildrenList(List<Word> wordList) {
        this.wordList = wordList;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
