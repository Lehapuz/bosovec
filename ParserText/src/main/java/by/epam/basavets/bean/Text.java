package by.epam.basavets.bean;

import java.util.List;

public class Text {
    private String text;
    private List<Paragraph> paragraphList;
    private List<Sentence> sentenceList;

    public Text(String text) {
        this.text = text;
    }

    public Text() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Paragraph> getParagraphList() {
        return paragraphList;
    }

    public void setParagraphList(List<Paragraph> paragraphList) {
        this.paragraphList = paragraphList;
    }

    public List<Sentence> getSentenceList() {
        return sentenceList;
    }

    public void setSentenceList(List<Sentence> sentenceList) {
        this.sentenceList = sentenceList;
    }
}
