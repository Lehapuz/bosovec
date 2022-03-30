package by.epam.basavets.bean;

import java.util.List;

public class Paragraph implements Comparable<Paragraph>, Parent<Sentence> {
    private String paragraph;
    private List<Sentence> sentenceList;

    public Paragraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public Paragraph() {
    }

    @Override
    public int compareTo(Paragraph o) {
        if (sentenceList.size() > o.sentenceList.size()) {
            return 1;
        }
        if (sentenceList.size() < o.sentenceList.size()) {
            return -1;
        }
        return 0;
    }

    @Override
    public String getParent() {
        return paragraph;
    }

    @Override
    public void setParent(String paragraph) {
        this.paragraph = paragraph;
    }

    @Override
    public List<Sentence> getChildrenList() {
        return sentenceList;
    }

    @Override
    public void setChildrenList(List<Sentence> sentenceList) {
        this.sentenceList = sentenceList;
    }
}
