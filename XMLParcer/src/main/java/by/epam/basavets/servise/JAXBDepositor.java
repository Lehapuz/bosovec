package by.epam.basavets.servise;

import by.epam.basavets.bean.TypeContribution;
import jakarta.xml.bind.annotation.*;

import javax.xml.bind.annotation.XmlAttribute;
import java.time.LocalDateTime;

@XmlRootElement(name = "banks")
@XmlType(propOrder = {"name", "country", "town"})
public class JAXBDepositor {

    private String name;
    private String country;
    private String town;




    private int id;
    private int amountOnDeposit;
    private double profitability;
    private LocalDateTime timeConstraints;
    private TypeContribution typeContribution;



//    @XmlElement(name = "id")
//    public void setId(int id){
//        this.id = id;
//    }
//
//    @XmlElement(name = "amountOnDeposit")
//    public void setAmountOnDeposit(int amountOnDeposit){
//        this.amountOnDeposit = amountOnDeposit;
//    }
//
//    @XmlElement(name = "profitability")
//    public void setProfitability(double profitability){
//        this.profitability = profitability;
//    }
//
//    @XmlElement(name = "timeConstraints")
//    public void setTimeConstraints(LocalDateTime timeConstraints){
//        this.timeConstraints = timeConstraints;
//    }
//
//    @XmlElement(name = "typeContribution")
//    public void setTypeContribution(TypeContribution typeContribution){
//        this.typeContribution = typeContribution;
//    }

    @XmlElement(name = "name")
    public void setName(String name){
        this.name = name;
    }

    @XmlElement(name = "country")
    public void setCountry(String country){
        this.country = country;
    }

    @XmlElement(name = "town")
    public void setTown(String town){
        this.town = town;
    }

    public int getId() {
        return id;
    }

    public int getAmountOnDeposit() {
        return amountOnDeposit;
    }

    public double getProfitability() {
        return profitability;
    }

    public LocalDateTime getTimeConstraints() {
        return timeConstraints;
    }

    public TypeContribution getTypeContribution() {
        return typeContribution;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getTown() {
        return town;
    }
}
