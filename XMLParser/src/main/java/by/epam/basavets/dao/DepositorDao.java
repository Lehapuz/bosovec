package by.epam.basavets.dao;

import by.epam.basavets.bean.Depositor;

import java.util.ArrayList;
import java.util.List;

public class DepositorDao {
    private final List<Depositor> depositors = new ArrayList<>();


    public void addDepositor(Depositor depositor) {
        depositors.add(depositor);
    }

    public void printDepositor() {
        for (Depositor depositor : depositors) {
            System.out.println("Id вкладчика - " + depositor.getId() + " Сумма вклада - " + depositor.getAmountOnDeposit()
                    + " Процент по вкладу - " + depositor.getProfitability() + " Дата возврата вклада - "
                    + depositor.getTimeConstraints() + " Вид вклада - " + depositor.getTypeContribution());
        }
    }

    public Depositor getDepositor() {
        for (Depositor depositor : depositors) {
            return depositor;
        }
        return null;
    }

    public void clearDepositor() {
        depositors.clear();
    }
}
