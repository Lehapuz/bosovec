package by.epam.basavets.bean;

import java.time.LocalDateTime;

public class Depositor {
    private int id;
    private int amountOnDeposit;
    private double profitability;
    private LocalDateTime timeConstraints;
    private TypeContribution typeContribution;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmountOnDeposit() {
        return amountOnDeposit;
    }

    public void setAmountOnDeposit(int amountOnDeposit) {
        this.amountOnDeposit = amountOnDeposit;
    }

    public double getProfitability() {
        return profitability;
    }

    public void setProfitability(double profitability) {
        this.profitability = profitability;
    }

    public LocalDateTime getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(LocalDateTime timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    public TypeContribution getTypeContribution() {
        return typeContribution;
    }

    public void setTypeContribution(TypeContribution typeContribution) {
        this.typeContribution = typeContribution;
    }
}
