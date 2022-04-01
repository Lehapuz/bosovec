package by.epam.basavets.bean;

import java.util.List;

public class Text implements Node<Paragraph> {
    private String text;
    private List<Paragraph> paragraphList;

    public Text(String text) {
        this.text = text;
    }

    public Text() {
    }

    @Override
    public List<Paragraph> getChildrenList() {
        return paragraphList;
    }

    @Override
    public void setChildrenList(List<Paragraph> paragraphList) {
        this.paragraphList = paragraphList;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
