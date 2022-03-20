package by.epam.basavets.dao;

public class DataSource {

    private final BankDao bankDao;
    private final DepositorDao depositorDao;

    public DataSource(BankDao bankDao, DepositorDao depositorDao) {
        this.bankDao = bankDao;
        this.depositorDao = depositorDao;
    }

    public BankDao getBankDao() {
        return bankDao;
    }

    public DepositorDao getDepositorDao() {
        return depositorDao;
    }
}
