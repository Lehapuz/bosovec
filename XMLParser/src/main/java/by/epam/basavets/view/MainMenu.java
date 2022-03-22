package by.epam.basavets.view;

import by.epam.basavets.controller.Controller;
import jakarta.xml.bind.JAXBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Scanner;


public class MainMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final Logger logger = LogManager.getRootLogger();
    private final Controller controller = new Controller();

    public void run() throws ParserConfigurationException, SAXException, IOException, XMLStreamException, JAXBException {
        while (true) {
            logger.info("Список доступных команд:");
            logger.info("Использование DOM-парсера: нажмите 1");
            logger.info("Использование SAX-парсера: нажмите 2");
            logger.info("Использование StAX-парсера: нажмите 3");
            logger.info("Использование JAXB: нажмите 4");

            String input = scanner.nextLine();

            switch (input) {
                case "1" -> controller.runDOM();
                case "2" -> controller.runSAX();
                case "3" -> controller.runStAX();
                case "4" -> controller.runJAXB();
                default -> logger.error("Неверная команда ввода: " + input);
            }
        }
    }
}
