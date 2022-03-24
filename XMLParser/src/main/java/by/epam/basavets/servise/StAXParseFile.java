package by.epam.basavets.servise;

import by.epam.basavets.bean.Bank;
import by.epam.basavets.bean.Depositor;
import by.epam.basavets.bean.TypeContribution;
import by.epam.basavets.dao.DataSource;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class StAXParseFile {

    private final DataSource dataSource;
    private final List<Depositor> depositors = new ArrayList<>();
    private final Set<Depositor> depositorSet = new TreeSet<>();
    private final Set<Bank> bankSet = new TreeSet<>();

    public StAXParseFile(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void parseFile(String pathFile) throws IOException, XMLStreamException {

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(pathFile));

        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();
            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                if ("bank".equals(startElement.getName().getLocalPart())) {
                    depositors.clear();
                    Bank bank = new Bank();
                    dataSource.getBankDao().addBank(bank);
                }
                if ("name".equals(startElement.getName().getLocalPart())) {
                    nextEvent = reader.nextEvent();
                    dataSource.getBankDao().getBank().setName(nextEvent.asCharacters().getData());
                }
                if ("country".equals(startElement.getName().getLocalPart())) {
                    nextEvent = reader.nextEvent();
                    dataSource.getBankDao().getBank().setCountry(nextEvent.asCharacters().getData());
                }
                if ("town".equals(startElement.getName().getLocalPart())) {
                    nextEvent = reader.nextEvent();
                    dataSource.getBankDao().getBank().setTown(nextEvent.asCharacters().getData());
                }
                if ("depositor".equals(startElement.getName().getLocalPart())) {
                    Depositor depositor = new Depositor();
                    dataSource.getDepositorDao().addDepositor(depositor);
                }
                if ("id".equals(startElement.getName().getLocalPart())) {
                    nextEvent = reader.nextEvent();
                    dataSource.getDepositorDao().getDepositor().setId(Integer.parseInt(nextEvent
                            .asCharacters().getData()));
                }
                if ("amountOnDeposit".equals(startElement.getName().getLocalPart())) {
                    nextEvent = reader.nextEvent();
                    dataSource.getDepositorDao().getDepositor().setAmountOnDeposit(Integer
                            .parseInt(nextEvent.asCharacters().getData()));
                }
                if ("profitability".equals(startElement.getName().getLocalPart())) {
                    nextEvent = reader.nextEvent();
                    dataSource.getDepositorDao().getDepositor().setProfitability(Double
                            .parseDouble(nextEvent.asCharacters().getData()));
                }
                if ("timeConstraints".equals(startElement.getName().getLocalPart())) {
                    nextEvent = reader.nextEvent();
                    dataSource.getDepositorDao().getDepositor().setTimeConstraints(LocalDateTime
                            .parse(nextEvent.asCharacters().getData()));
                }
                if ("typeContribution".equals(startElement.getName().getLocalPart())
                        && dataSource.getDepositorDao().getDepositor() != null) {
                    nextEvent = reader.nextEvent();
                    dataSource.getDepositorDao().getDepositor().setTypeContribution(TypeContribution
                            .valueOf(nextEvent.asCharacters().getData()));
                }
            }
            if (nextEvent.isEndElement()) {
                EndElement endElement = nextEvent.asEndElement();
                if ("bank".equals(endElement.getName().getLocalPart())) {
                    dataSource.getBankDao().getBank().setDepositors(depositors);
                    bankSet.add(dataSource.getBankDao().getBank());
                    dataSource.getBankDao().clearBank();
                }
                if ("depositor".equals(endElement.getName().getLocalPart())) {
                    depositors.add(dataSource.getDepositorDao().getDepositor());
                    depositorSet.add(dataSource.getDepositorDao().getDepositor());
                    dataSource.getDepositorDao().clearDepositor();
                }
            }
        }
        for (Bank bank : bankSet) {
            dataSource.getBankDao().addBank(bank);
        }
        dataSource.getBankDao().printBank();
        for (Depositor depositor : depositorSet) {
            dataSource.getDepositorDao().addDepositor(depositor);
        }
        dataSource.getDepositorDao().printDepositor();
    }
}
