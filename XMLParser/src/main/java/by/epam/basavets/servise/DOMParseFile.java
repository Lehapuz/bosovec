package by.epam.basavets.servise;

import by.epam.basavets.bean.Bank;
import by.epam.basavets.bean.Depositor;
import by.epam.basavets.bean.TypeContribution;
import by.epam.basavets.dao.DataSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DOMParseFile {

    private final DataSource dataSource;

    public DOMParseFile(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void parseFile(String pathFile) throws ParserConfigurationException, IOException, SAXException {
        dataSource.getBankDao().clearBank();
        dataSource.getDepositorDao().clearDepositor();

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new File(pathFile));
        doc.getDocumentElement().normalize();
        NodeList bankList = doc.getElementsByTagName("bank");

        for (int i = 0; i < bankList.getLength(); i++) {
            Bank bank = new Bank();
            List<Depositor> depositors = new ArrayList<>();
            Node bankNode = bankList.item(i);
            NodeList bankElements = bankNode.getChildNodes();

            for (int j = 0; j < bankElements.getLength(); j++) {
                Node bankElement = bankElements.item(j);
                if ("name".equals(bankElement.getNodeName())) {
                    bank.setName(bankElement.getTextContent());
                }
                if ("country".equals(bankElement.getNodeName())) {
                    bank.setCountry(bankElement.getTextContent());
                }
                if ("town".equals(bankElement.getNodeName())) {
                    bank.setTown(bankElement.getTextContent());
                }
                if ("depositor".equals(bankElement.getNodeName())) {
                    Depositor depositor = new Depositor();
                    NodeList depositorList = bankElement.getChildNodes();
                    for (int k = 0; k < depositorList.getLength(); k++) {
                        Node depositorElement = depositorList.item(k);
                        if ("id".equals(depositorElement.getNodeName())) {
                            depositor.setId(Integer.parseInt(depositorElement.getTextContent()));
                        }
                        if ("amountOnDeposit".equals(depositorElement.getNodeName())) {
                            depositor.setAmountOnDeposit(Integer.parseInt(depositorElement.getTextContent()));
                        }
                        if ("profitability".equals(depositorElement.getNodeName())) {
                            depositor.setProfitability(Double.parseDouble(depositorElement.getTextContent()));
                        }
                        if ("timeConstraints".equals(depositorElement.getNodeName())) {
                            depositor.setTimeConstraints(LocalDateTime.parse(depositorElement.getTextContent()));
                        }
                        if ("typeContribution".equals(depositorElement.getNodeName())) {
                            depositor.setTypeContribution(TypeContribution.valueOf(depositorElement.getTextContent()));
                        }
                    }
                    dataSource.getDepositorDao().addDepositor(depositor);
                    depositors.add(depositor);
                    bank.setDepositors(depositors);
                }
            }
            dataSource.getBankDao().addBank(bank);
        }
        dataSource.getBankDao().printBank();
        dataSource.getDepositorDao().printDepositor();
    }
}
