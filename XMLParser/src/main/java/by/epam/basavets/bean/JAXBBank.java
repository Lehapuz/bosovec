package by.epam.basavets.bean;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "bank")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bank", propOrder = {"name", "country", "town", "typeContribution", "depositorList"})
public class JAXBBank {

    @XmlElement(name = "name", required = true)
    private String name;
    @XmlElement(name = "country", required = true)
    private String country;
    @XmlElement(name = "town", required = true)
    private String town;
    @XmlElement(name = "typeContribution")
    private TypeContribution typeContribution;
    @XmlElement(name = "depositor")
    private List<JAXBDepositor> depositorList = new ArrayList<>();

    public List<JAXBDepositor> getDepositors() {
        return depositorList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public TypeContribution getTypeContribution() {
        return typeContribution;
    }

    public void setTypeContribution(TypeContribution typeContribution) {
        this.typeContribution = typeContribution;
    }
}
