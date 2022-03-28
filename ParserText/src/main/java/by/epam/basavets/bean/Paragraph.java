package by.epam.basavets.bean;

import java.util.List;

public class Paragraph implements Comparable<Paragraph> {
    private String paragraph;
    private List<Sentence> sentenceList;

    public Paragraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public Paragraph() {
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public List<Sentence> getSentenceList() {
        return sentenceList;
    }

    public void setSentenceList(List<Sentence> sentenceList) {
        this.sentenceList = sentenceList;
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
}
