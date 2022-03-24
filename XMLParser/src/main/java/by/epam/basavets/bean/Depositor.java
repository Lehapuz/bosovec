package by.epam.basavets.bean;

import java.time.LocalDateTime;

public class Depositor implements Comparable<Depositor> {
    private int id;
    private int amountOnDeposit;
    private double profitability;
    private LocalDateTime timeConstraints;
    private TypeContribution typeContribution;

    public Depositor(int id, int amountOnDeposit, double profitability, LocalDateTime timeConstraints,
                     TypeContribution typeContribution){
        this.id = id;
        this.amountOnDeposit = amountOnDeposit;
        this.profitability = profitability;
        this.timeConstraints = timeConstraints;
        this.typeContribution = typeContribution;
    }

    public Depositor(){}

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

    @Override
    public int compareTo(Depositor o) {
        return Integer.compare(getId(), o.getId());
    }
}
