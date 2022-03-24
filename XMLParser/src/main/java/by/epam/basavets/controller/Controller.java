package by.epam.basavets.controller;

import by.epam.basavets.dao.BankDao;
import by.epam.basavets.dao.DataSource;
import by.epam.basavets.dao.DepositorDao;
import by.epam.basavets.servise.DOMParseFile;
import by.epam.basavets.servise.JAXBParseFile;
import by.epam.basavets.servise.SAXParseFile;
import by.epam.basavets.servise.StAXParseFile;
import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;

public class Controller {

    private final BankDao bankDao = new BankDao();
    private final DepositorDao depositorDao = new DepositorDao();
    private final DataSource dataSource = new DataSource(bankDao, depositorDao);
    private final DOMParseFile domParseFile = new DOMParseFile(dataSource);
    private final SAXParseFile saxParseFile = new SAXParseFile(dataSource);
    private final StAXParseFile staxParseFile = new StAXParseFile(dataSource);
    private final JAXBParseFile jaxbParseFile = new JAXBParseFile(dataSource);
    private final String PATH_FILE = "src/main/resources/banks.xml";
    private final File FILE = new File("src/main/resources/banks.xml");


    public Controller() {
    }

    public void runDOM() throws IOException, SAXException, ParserConfigurationException {
        domParseFile.parseFile(PATH_FILE);
    }

    public void runSAX() throws IOException, SAXException, ParserConfigurationException {
        saxParseFile.parseFile(PATH_FILE);
    }

    public void runStAX() throws IOException, XMLStreamException {
        staxParseFile.parseFile(PATH_FILE);
    }

    public void runJAXB() throws JAXBException {
        jaxbParseFile.parseFile(FILE);
    }
}
