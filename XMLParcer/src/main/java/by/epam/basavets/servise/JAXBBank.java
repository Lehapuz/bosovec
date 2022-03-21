package by.epam.basavets.servise;

import by.epam.basavets.bean.Depositor;
import by.epam.basavets.bean.TypeContribution;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.time.LocalDateTime;

@XmlRootElement(name = "banks")
@XmlType(propOrder = {"name", "country", "town", "depositor", "id", "amountOnDeposit", "profitability",
"timeConstraints", "typeContribution"})
public class JAXBBank {


        private String name;
        private String country;
        private String town;
        private int id;
        private int amountOnDeposit;
        private double profitability;
        private LocalDateTime timeConstraints;
        private TypeContribution typeContribution;
        private Depositor depositor;

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

    @XmlElement(name = "depositor")
    public void setDepositor(Depositor depositor){
        this.depositor = depositor;
    }

    @XmlElement(name = "id")
    public void setId(int id){
        this.id = id;
    }

    @XmlElement(name = "amountOnDeposit")
    public void setAmountOnDeposit(int amountOnDeposit){
        this.amountOnDeposit = amountOnDeposit;
    }

    @XmlElement(name = "profitability")
    public void setProfitability(double profitability){
        this.profitability = profitability;
    }

    @XmlElement(name = "timeConstraints")
    public void setTimeConstraints(LocalDateTime timeConstraints){
        this.timeConstraints = timeConstraints;
    }

    @XmlElement(name = "typeContribution")
    public void setTypeContribution(TypeContribution typeContribution){
        this.typeContribution = typeContribution;
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

    public Depositor getDepositor() {
        return depositor;
    }
}
