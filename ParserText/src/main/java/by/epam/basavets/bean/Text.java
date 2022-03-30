package by.epam.basavets.bean;

import java.util.List;

public class Text implements Parent<Paragraph> {
    private String text;
    private List<Paragraph> paragraphList;

    public Text(String text) {
        this.text = text;
    }

    public Text() {
    }

    @Override
    public String getParent() {
        return text;
    }

    @Override
    public void setParent(String text) {
        this.text = text;
    }

    @Override
    public List<Paragraph> getChildrenList() {
        return paragraphList;
    }

    @Override
    public void setChildrenList(List<Paragraph> paragraphList) {
        this.paragraphList = paragraphList;
    }
}
