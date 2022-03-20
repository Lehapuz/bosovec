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
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new File(pathFile));
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("bank");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Bank bank = new Bank();
            List<Depositor> depositors = new ArrayList<>();
            Node node = nodeList.item(i);
            NodeList nodeList1 = node.getChildNodes();

            for (int j = 0; j < nodeList1.getLength(); j++) {
                Node node1 = nodeList1.item(j);
                if (node1.getNodeName().equals("name")) {
                    bank.setName(node1.getTextContent());
                }
                if (node1.getNodeName().equals("country")) {
                    bank.setCountry(node1.getTextContent());
                }
                if (node1.getNodeName().equals("town")) {
                    bank.setTown(node1.getTextContent());
                }
                if (node1.getNodeName().equals("depositor")) {
                    Depositor depositor = new Depositor();
                    NodeList nodeList2 = node1.getChildNodes();
                    for (int k = 0; k < nodeList2.getLength(); k++) {
                        Node node2 = nodeList2.item(k);
                        if (node2.getNodeName().equals("id")) {
                            depositor.setId(Integer.parseInt(node2.getTextContent()));
                        }
                        if (node2.getNodeName().equals("amountOnDeposit")) {
                            depositor.setAmountOnDeposit(Integer.parseInt(node2.getTextContent()));
                        }
                        if (node2.getNodeName().equals("profitability")) {
                            depositor.setProfitability(Double.parseDouble(node2.getTextContent()));
                        }
                        if (node2.getNodeName().equals("timeConstraints")) {
                            depositor.setTimeConstraints(LocalDateTime.parse(node2.getTextContent()));
                        }
                        if (node2.getNodeName().equals("typeContribution")) {
                            depositor.setTypeContribution(TypeContribution.valueOf(node2.getTextContent()));
                        }
                    }
                    dataSource.getDepositorDao().addDepositor(depositor);
                    depositors.add(depositor);
                    bank.setDepositors(depositors);
                }
            }
            dataSource.getBankDao().addBank(bank);
        }
        dataSource.getBankDao().getBank();
        dataSource.getDepositorDao().getDepositor();
    }
}
