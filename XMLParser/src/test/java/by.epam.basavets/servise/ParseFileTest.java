package by.epam.basavets.servise;

import by.epam.basavets.bean.Bank;
import by.epam.basavets.bean.Depositor;
import by.epam.basavets.bean.TypeContribution;
import by.epam.basavets.controller.Controller;
import by.epam.basavets.dao.BankDao;
import by.epam.basavets.dao.DataSource;
import by.epam.basavets.dao.DepositorDao;
import jakarta.xml.bind.JAXBException;
import junit.framework.TestCase;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DOMParseFileTest extends TestCase {
    Bank expectedBank = new Bank("DeutscheBank", "Germany", "Berlin");

    Depositor expectedDepositor1 = new Depositor(1, 100000, 0.3, LocalDateTime.parse("2022-03-14T00:00:00"), TypeContribution.urgent);
    Depositor expectedDepositor2 = new Depositor(2, 200000, 0.25, LocalDateTime.parse("2025-03-14T00:00"), TypeContribution.accumulative);
    Depositor expectedDepositor3 = new Depositor(3, 2000000, 0.4, LocalDateTime.parse("2025-05-14T00:00"), TypeContribution.toDemand);
    Depositor expectedDepositor4 = new Depositor(4, 5000000, 0.45, LocalDateTime.parse("2025-03-14T00:00"), TypeContribution.accumulative);

    List<Depositor> depositors1 = new ArrayList<>();

    BankDao bankDao = new BankDao();
    DepositorDao depositorDao = new DepositorDao();
    DataSource dataSource = new DataSource(bankDao, depositorDao);
    DOMParseFile domParseFile = new DOMParseFile(dataSource);
    SAXParseFile saxParseFile = new SAXParseFile(dataSource);
    StAXParseFile stAXParseFile = new StAXParseFile(dataSource);
    JAXBParseFile jaxbParseFile = new JAXBParseFile(dataSource);
    File FILE = new File("src/main/resources/banks.xml");


    protected void setUp() {
        depositors1.add(expectedDepositor1);
        depositors1.add(expectedDepositor2);
        depositors1.add(expectedDepositor3);
        depositors1.add(expectedDepositor4);

        bankDao.addBank(expectedBank);

        depositorDao.addDepositor(expectedDepositor1);
        depositorDao.addDepositor(expectedDepositor2);
        depositorDao.addDepositor(expectedDepositor3);
        depositorDao.addDepositor(expectedDepositor4);

        expectedBank.setDepositors(depositors1);
    }

    public void testDOMParseFile() throws ParserConfigurationException, SAXException, IOException {

        domParseFile.parseFile("src/main/resources/banks.xml");
        Depositor actualDepositor = dataSource.getDepositorDao().getDepositor();
        Bank actualBank = dataSource.getBankDao().getBank();
        assertEquals(expectedDepositor1.getId(), actualDepositor.getId());
        assertEquals(expectedBank.getDepositors().size(), actualBank.getDepositors().size());
    }


    public void testSAXParseFile() throws ParserConfigurationException, SAXException, IOException {
        saxParseFile.parseFile("src/main/resources/banks.xml");
        Depositor actualDepositor = dataSource.getDepositorDao().getDepositor();
        Bank actualBank = dataSource.getBankDao().getBank();
        assertEquals(expectedDepositor1.getId(), actualDepositor.getId());
        assertEquals(expectedBank.getDepositors().size(), actualBank.getDepositors().size());
    }

    public void testStAXParseFile() throws IOException, XMLStreamException {
        stAXParseFile.parseFile("src/main/resources/banks.xml");
        Depositor actualDepositor = dataSource.getDepositorDao().getDepositor();
        Bank actualBank = dataSource.getBankDao().getBank();
        assertEquals(expectedDepositor1.getId(), actualDepositor.getId());
        assertEquals(expectedBank.getDepositors().size(), actualBank.getDepositors().size());
    }

    public void testJAXBParseFile() throws JAXBException {
        jaxbParseFile.parseFile(FILE);
        Depositor actualDepositor = dataSource.getDepositorDao().getDepositor();
        Bank actualBank = dataSource.getBankDao().getBank();
        assertEquals(expectedDepositor1.getId(), actualDepositor.getId());
        assertEquals(expectedBank.getDepositors().size(), actualBank.getJaxbDepositors().size());
    }


    }

