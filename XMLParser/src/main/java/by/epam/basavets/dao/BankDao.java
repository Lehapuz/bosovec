package by.epam.basavets.dao;

import by.epam.basavets.bean.Bank;

import java.util.ArrayList;
import java.util.List;

public class BankDao {
    private final List<Bank> banks = new ArrayList<>();

    public void addBank(Bank bank) {
        banks.add(bank);
    }

    public void printBank() {
        for (Bank bank : banks) {
            System.out.println("Название банка - " + bank.getName() + " Страна - " + bank.getCountry() + " Город - "
                    + bank.getTown() + " Количество вкладчиков банка - " + bank.getDepositors().size());
        }
    }

    public void printJAXBBank() {
        for (Bank bank : banks) {
            System.out.println("Название банка - " + bank.getName() + " Страна - " + bank.getCountry() + " Город - "
                    + bank.getTown() + " Количество вкладчиков банка - " + bank.getJaxbDepositors().size());
        }
    }

    public Bank getBank() {
        for (Bank bank : banks) {
            return bank;
        }
        return null;
    }

    public void clearBank() {
        banks.clear();
    }
}
