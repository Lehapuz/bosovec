package by.epam.basavets.servise;

import by.epam.basavets.bean.Bank;
import by.epam.basavets.bean.Depositor;
import by.epam.basavets.bean.TypeContribution;
import by.epam.basavets.dao.DataSource;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.util.*;

public class XMLHandler extends DefaultHandler {

    private final DataSource dataSource;
    private StringBuilder elementValue;
    private final List<Depositor> depositors = new ArrayList<>();
    private final Set<Depositor> depositorSet = new TreeSet<>();
    private final Set<Bank> bankSet = new TreeSet<>();


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
        if ("bank".equals(qName)) {
            depositors.clear();
            Bank bank = new Bank();
            elementValue = new StringBuilder();
            dataSource.getBankDao().addBank(bank);
        }
        if ("name".equals(qName)) {
            elementValue = new StringBuilder();
        }
        if ("country".equals(qName)) {
            elementValue = new StringBuilder();
        }
        if ("town".equals(qName)) {
            elementValue = new StringBuilder();
        }
        if ("depositor".equals(qName)) {
            Depositor depositor = new Depositor();
            elementValue = new StringBuilder();
            dataSource.getDepositorDao().addDepositor(depositor);
        }
        if ("id".equals(qName)) {
            elementValue = new StringBuilder();
        }
        if ("amountOnDeposit".equals(qName)) {
            elementValue = new StringBuilder();
        }
        if ("profitability".equals(qName)) {
            elementValue = new StringBuilder();
        }
        if ("timeConstraints".equals(qName)) {
            elementValue = new StringBuilder();
        }
        if ("typeContribution".equals(qName)) {
            elementValue = new StringBuilder();
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("bank".equals(qName)) {
            dataSource.getBankDao().getBank().setDepositors(depositors);
            bankSet.add(dataSource.getBankDao().getBank());
            dataSource.getBankDao().clearBank();
        }
        if ("name".equals(qName)) {
            dataSource.getBankDao().getBank().setName(elementValue.toString());
        }
        if ("country".equals(qName)) {
            dataSource.getBankDao().getBank().setCountry(elementValue.toString());
        }
        if ("town".equals(qName)) {
            dataSource.getBankDao().getBank().setTown(elementValue.toString());
        }
        if ("depositor".equals(qName)) {
            depositors.add(dataSource.getDepositorDao().getDepositor());
            depositorSet.add(dataSource.getDepositorDao().getDepositor());
            dataSource.getDepositorDao().clearDepositor();
        }
        if ("id".equals(qName)) {
            dataSource.getDepositorDao().getDepositor().setId(Integer.parseInt(elementValue.toString()));
        }
        if ("amountOnDeposit".equals(qName)) {
            dataSource.getDepositorDao().getDepositor().setAmountOnDeposit(Integer.parseInt(elementValue.toString()));
        }
        if ("profitability".equals(qName)) {
            dataSource.getDepositorDao().getDepositor().setProfitability(Double.parseDouble(elementValue.toString()));
        }
        if ("timeConstraints".equals(qName)) {
            dataSource.getDepositorDao().getDepositor().setTimeConstraints(LocalDateTime.parse(elementValue.toString()));
        }
        if ("typeContribution".equals(qName) && dataSource.getDepositorDao().getDepositor() != null) {
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
