package by.epam.basavets.controller;

import by.epam.basavets.dao.BankDao;
import by.epam.basavets.dao.DataSource;
import by.epam.basavets.dao.DepositorDao;
import by.epam.basavets.servise.DOMParseFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Controller {
    BankDao bankDao = new BankDao();
    DepositorDao depositorDao = new DepositorDao();
    DataSource dataSource = new DataSource(bankDao, depositorDao);
    DOMParseFile domParseFile = new DOMParseFile(dataSource);
    private final String filePath = "src/main/resources/banks.xml";

    public void run() throws IOException, SAXException, ParserConfigurationException {
        domParseFile.parseFile(filePath);
    }
}
