package by.epam.basavets.servise;

import by.epam.basavets.bean.Bank;
import by.epam.basavets.bean.Depositor;
import by.epam.basavets.bean.TypeContribution;
import by.epam.basavets.dao.DataSource;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class XMLHandler extends DefaultHandler {

    private final DataSource dataSource;
    private StringBuilder elementValue;
    private final List<Depositor> depositors = new ArrayList<>();
    private final Set<Depositor> depositorSet = new HashSet<>();
    private final Set<Bank> bankSet = new HashSet<>();


    public XMLHandler(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void characters(char[] ch, int start, int length) {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("bank")) {
            depositors.clear();
            Bank bank = new Bank();
            elementValue = new StringBuilder();
            dataSource.getBankDao().addBank(bank);
        }
        if (qName.equals("name")) {
            elementValue = new StringBuilder();
        }
        if (qName.equals("country")) {
            elementValue = new StringBuilder();
        }
        if (qName.equals("town")) {
            elementValue = new StringBuilder();
        }
        if (qName.equals("depositor")) {
            Depositor depositor = new Depositor();
            elementValue = new StringBuilder();
            dataSource.getDepositorDao().addDepositor(depositor);
        }
        if (qName.equals("id")) {
            elementValue = new StringBuilder();
        }
        if (qName.equals("amountOnDeposit")) {
            elementValue = new StringBuilder();
        }
        if (qName.equals("profitability")) {
            elementValue = new StringBuilder();
        }
        if (qName.equals("timeConstraints")) {
            elementValue = new StringBuilder();
        }
        if (qName.equals("typeContribution")) {
            elementValue = new StringBuilder();
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("bank")) {
            dataSource.getBankDao().getBank().setDepositors(depositors);
            bankSet.add(dataSource.getBankDao().getBank());
            dataSource.getBankDao().clearBank();
        }
        if (qName.equals("name")) {
            dataSource.getBankDao().getBank().setName(elementValue.toString());
        }
        if (qName.equals("country")) {
            dataSource.getBankDao().getBank().setCountry(elementValue.toString());
        }
        if (qName.equals("town")) {
            dataSource.getBankDao().getBank().setTown(elementValue.toString());
        }
        if (qName.equals("depositor")) {
            depositors.add(dataSource.getDepositorDao().getDepositor());
            depositorSet.add(dataSource.getDepositorDao().getDepositor());
            dataSource.getDepositorDao().clearDepositor();
        }
        if (qName.equals("id")) {
            dataSource.getDepositorDao().getDepositor().setId(Integer.parseInt(elementValue.toString()));
        }

        if (qName.equals("amountOnDeposit")) {
            dataSource.getDepositorDao().getDepositor().setAmountOnDeposit(Integer.parseInt(elementValue.toString()));
        }
        if (qName.equals("profitability")) {
            dataSource.getDepositorDao().getDepositor().setProfitability(Double.parseDouble(elementValue.toString()));
        }
        if (qName.equals("timeConstraints")) {
            dataSource.getDepositorDao().getDepositor().setTimeConstraints(LocalDateTime.parse(elementValue.toString()));
        }
        if (qName.equals("typeContribution") && dataSource.getDepositorDao().getDepositor() != null) {
            dataSource.getDepositorDao().getDepositor().setTypeContribution(TypeContribution.valueOf(elementValue.toString()));
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
