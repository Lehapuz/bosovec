package by.epam.basavets;

import by.epam.basavets.controller.Controller;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main (String[] args) throws ParserConfigurationException, SAXException, IOException {
        Controller controller = new Controller();
        controller.run();
    }
}
