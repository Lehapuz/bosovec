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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StAXParseFile {

    private final DataSource dataSource;
    private final List<Depositor> depositors = new ArrayList<>();
    private final Set<Depositor> depositorSet = new HashSet<>();
    private final Set<Bank> bankSet = new HashSet<>();

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
                if (startElement.getName().getLocalPart().equals("bank")) {
                    depositors.clear();
                    Bank bank = new Bank();
                    dataSource.getBankDao().addBank(bank);
                }
                if (startElement.getName().getLocalPart().equals("name")) {
                    nextEvent = reader.nextEvent();
                    dataSource.getBankDao().getBank().setName(nextEvent.asCharacters().getData());
                }
                if (startElement.getName().getLocalPart().equals("country")) {
                    nextEvent = reader.nextEvent();
                    dataSource.getBankDao().getBank().setCountry(nextEvent.asCharacters().getData());
                }
                if (startElement.getName().getLocalPart().equals("town")) {
                    nextEvent = reader.nextEvent();
                    dataSource.getBankDao().getBank().setTown(nextEvent.asCharacters().getData());
                }
                if (startElement.getName().getLocalPart().equals("depositor")) {
                    Depositor depositor = new Depositor();
                    dataSource.getDepositorDao().addDepositor(depositor);
                }
                if (startElement.getName().getLocalPart().equals("id")) {
                    nextEvent = reader.nextEvent();
                    dataSource.getDepositorDao().getDepositor().setId(Integer.parseInt(nextEvent
                            .asCharacters().getData()));
                }
                if (startElement.getName().getLocalPart().equals("amountOnDeposit")) {
                    nextEvent = reader.nextEvent();
                    dataSource.getDepositorDao().getDepositor().setAmountOnDeposit(Integer
                            .parseInt(nextEvent.asCharacters().getData()));
                }
                if (startElement.getName().getLocalPart().equals("profitability")) {
                    nextEvent = reader.nextEvent();
                    dataSource.getDepositorDao().getDepositor().setProfitability(Double
                            .parseDouble(nextEvent.asCharacters().getData()));
                }
                if (startElement.getName().getLocalPart().equals("timeConstraints")) {
                    nextEvent = reader.nextEvent();
                    dataSource.getDepositorDao().getDepositor().setTimeConstraints(LocalDateTime
                            .parse(nextEvent.asCharacters().getData()));
                }
                if (startElement.getName().getLocalPart().equals("typeContribution")
                        && dataSource.getDepositorDao().getDepositor() != null) {
                    nextEvent = reader.nextEvent();
                    dataSource.getDepositorDao().getDepositor().setTypeContribution(TypeContribution
                            .valueOf(nextEvent.asCharacters().getData()));
                }
            }
            if (nextEvent.isEndElement()) {
                EndElement endElement = nextEvent.asEndElement();
                if (endElement.getName().getLocalPart().equals("bank")) {
                    dataSource.getBankDao().getBank().setDepositors(depositors);
                    bankSet.add(dataSource.getBankDao().getBank());
                    dataSource.getBankDao().clearBank();
                }
                if (endElement.getName().getLocalPart().equals("depositor")) {
                    depositors.add(dataSource.getDepositorDao().getDepositor());
                    depositorSet.add(dataSource.getDepositorDao().getDepositor());
                    dataSource.getDepositorDao().clearDepositor();
                }
            }
        }
    }

    public void getDepositors() {
        for (Depositor depositor : depositorSet) {
            dataSource.getDepositorDao().addDepositor(depositor);
        }
        dataSource.getDepositorDao().printDepositor();
    }


    public void getBanks() {
        for (Bank bank : bankSet) {
            dataSource.getBankDao().addBank(bank);
        }
        dataSource.getBankDao().printBank();
    }
}
