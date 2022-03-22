package by.epam.basavets.servise;

import by.epam.basavets.dao.DataSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXParseFile {

    private final DataSource dataSource;

    public SAXParseFile(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void parseFile(String pathFile) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser parser = saxParserFactory.newSAXParser();
        XMLHandler xmlHandler = new XMLHandler(dataSource);
        parser.parse(new File(pathFile), xmlHandler);
        xmlHandler.getBanks();
        xmlHandler.getDepositors();
    }
}
