package by.epam.basavets;

import by.epam.basavets.view.MainMenu;
import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Main {
    public static void main (String[] args) throws ParserConfigurationException, SAXException, IOException, JAXBException, XMLStreamException {
        MainMenu mainMenu = new MainMenu();
        mainMenu.run();
    }
}
