package by.epam.basavets.bean;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "depositor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "depositor", propOrder = {"id", "amountOnDeposit", "profitability", "timeConstraints", "typeContribution"})
public class JAXBDepositor {

    @XmlElement(name = "id", required = true)
    private int id;
    @XmlElement(name = "amountOnDeposit", required = true)
    private int amountOnDeposit;
    @XmlElement(name = "profitability", required = true)
    private double profitability;
    @XmlElement(name = "timeConstraints", required = true)
    private String timeConstraints;
    @XmlElement(name = "typeContribution", required = true)
    private TypeContribution typeContribution;

    public int getId() {
        return id;
    }

    public int getAmountOnDeposit() {
        return amountOnDeposit;
    }

    public double getProfitability() {
        return profitability;
    }

    public String getTimeConstraints() {
        return timeConstraints;
    }

    public TypeContribution getTypeContribution() {
        return typeContribution;
    }
}
