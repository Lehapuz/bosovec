package by.epam.basavets.storage;

import by.epam.basavets.bean.Paragraph;
import by.epam.basavets.bean.Sentence;
import by.epam.basavets.bean.Text;
import by.epam.basavets.bean.Word;

public class TextStorage {
    private Text text;
    private Paragraph paragraph;
    private Sentence sentence;
    private Word word;


    public TextStorage(Text text) {
        this.text = text;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Paragraph getParagraph() {
        return paragraph;
    }

    public void setParagraph(Paragraph paragraph) {
        this.paragraph = paragraph;
    }

    public Sentence getSentence() {
        return sentence;
    }

    public void setSentence(Sentence sentence) {
        this.sentence = sentence;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
