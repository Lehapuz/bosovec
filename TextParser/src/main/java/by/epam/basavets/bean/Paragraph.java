package by.epam.basavets.bean;

import java.util.List;

public class Paragraph implements Comparable<Paragraph>, Node<Sentence> {
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
    public List<Sentence> getChildrenList() {
        return sentenceList;
    }

    @Override
    public void setChildrenList(List<Sentence> sentenceList) {
        this.sentenceList = sentenceList;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }
}
