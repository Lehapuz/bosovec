package by.epam.basavets;

import by.epam.basavets.controller.Controller;
import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Main {
    public static void main (String[] args) throws ParserConfigurationException, SAXException, IOException, JAXBException, XMLStreamException {
        Controller controller = new Controller();
        System.out.println("Парсинг дом парсером");
        controller.runDom();
        System.out.println("Парсинг сакс парсером");
        controller.runSux();
        System.out.println("Парсинг стакс парсером");
        controller.runStux();
        //controller.printJAXB();
    }
}
