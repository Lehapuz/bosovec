package by.epam.basavets.servise;

import by.epam.basavets.bean.*;
import by.epam.basavets.dao.DataSource;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.time.LocalDateTime;

public class JAXBParseFile {

    private final DataSource dataSource;

    public JAXBParseFile(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void parseFile(File file) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(JAXBBanks.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        unmarshaller.setEventHandler(
                validationEvent -> {
                    throw new RuntimeException(validationEvent.getMessage(),
                            validationEvent.getLinkedException());
                });

        JAXBBanks jaxbBanks = (JAXBBanks) unmarshaller.unmarshal(file);

        for (JAXBBank jaxbBank : jaxbBanks.getBanks()) {
            Bank bank = new Bank();
            bank.setName(jaxbBank.getName());
            bank.setCountry(jaxbBank.getCountry());
            bank.setTown(jaxbBank.getTown());
            bank.setJaxbDepositors(jaxbBank.getDepositors());
            dataSource.getBankDao().addBank(bank);

            for (JAXBDepositor jaxbDepositor : jaxbBank.getDepositors()) {
                Depositor depositor = new Depositor();
                depositor.setId(jaxbDepositor.getId());
                depositor.setAmountOnDeposit(jaxbDepositor.getAmountOnDeposit());
                depositor.setProfitability(jaxbDepositor.getProfitability());
                depositor.setTimeConstraints(LocalDateTime
                        .parse(jaxbDepositor.getTimeConstraints()));
                depositor.setTypeContribution(jaxbDepositor.getTypeContribution());
                dataSource.getDepositorDao().addDepositor(depositor);
            }
        }
    }


    public void printJAXB() {
        dataSource.getBankDao().printJAXBBank();
        dataSource.getDepositorDao().printDepositor();
    }
}
