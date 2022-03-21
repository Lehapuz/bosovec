package by.epam.basavets.controller;

import by.epam.basavets.dao.BankDao;
import by.epam.basavets.dao.DataSource;
import by.epam.basavets.dao.DepositorDao;
import by.epam.basavets.servise.*;
import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {
    BankDao bankDao = new BankDao();
    DepositorDao depositorDao = new DepositorDao();
    DataSource dataSource = new DataSource(bankDao, depositorDao);
    DOMParseFile domParseFile = new DOMParseFile(dataSource);
    SAXParseFile saxParseFile = new SAXParseFile(dataSource);
    StAXParseFile staxParseFile = new StAXParseFile(dataSource);
    private final String filePath = "src/main/resources/banks.xml";

    JAXBDepositor jaxbDepositor = new JAXBDepositor();
    JAXBParseFile jaxbParseFile = new JAXBParseFile();


    public void runDom() throws IOException, SAXException, ParserConfigurationException {
        domParseFile.parseFile(filePath);
    }

    public void runSux() throws IOException, SAXException, ParserConfigurationException {
        saxParseFile.parseFile(filePath);
    }

    public void runStux() throws IOException, SAXException, ParserConfigurationException, XMLStreamException {
        staxParseFile.parseFile(filePath);
        staxParseFile.getBanks();
        staxParseFile.getDepositors();
    }

    public void printJAXB() throws JAXBException, FileNotFoundException {
        jaxbParseFile.parseFile(filePath);

    }


}
