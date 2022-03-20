package by.epam.basavets.dao;

import by.epam.basavets.bean.Bank;

import java.util.ArrayList;
import java.util.List;

public class BankDao {
    private final List<Bank> banks = new ArrayList<>();

    public void addBank(Bank bank) {
        banks.add(bank);
    }

    public void getBank() {
        for (Bank bank : banks) {
            System.out.println("Название банка - " + bank.getName() + " Страна - " + bank.getCountry() + " Город - "
                    + bank.getTown() + " Количество вкладчиков банка - " + bank.getDepositors().size());
        }
    }
}
