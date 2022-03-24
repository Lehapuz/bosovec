package by.epam.basavets.bean;

import java.util.List;

public class Bank implements Comparable<Bank>{
    private String name;
    private String country;
    private String town;
    private List<Depositor> depositors;
    private List<JAXBDepositor> jaxbDepositors;

    public Bank(String name, String country, String town){
        this.name = name;
        this.country = country;
        this.town = town;
    }

    public Bank(){}

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

    public List<Depositor> getDepositors() {
        return depositors;
    }

    public void setDepositors(List<Depositor> depositors) {
        this.depositors = depositors;
    }


    public List<JAXBDepositor> getJaxbDepositors() {
        return jaxbDepositors;
    }

    public void setJaxbDepositors(List<JAXBDepositor> jaxbDepositors) {
        this.jaxbDepositors = jaxbDepositors;
    }

    @Override
    public int compareTo(Bank o) {
        return CharSequence.compare(o.getName(), getName());
    }
}
