package by.epam.basavets.bean;

import java.util.List;

public class Sentence implements Parent<Word> {
    private String sentence;
    private List<Word> wordList;

    public Sentence(String sentence) {
        this.sentence = sentence;
    }

    public Sentence() {
    }

    @Override
    public String getParent() {
        return sentence;
    }

    @Override
    public void setParent(String sentence) {
        this.sentence = sentence;
    }

    @Override
    public List<Word> getChildrenList() {
        return wordList;
    }

    @Override
    public void setChildrenList(List<Word> wordList) {
        this.wordList = wordList;
    }
}
