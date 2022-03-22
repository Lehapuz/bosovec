package by.epam.basavets.bean;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "banks")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "banks", propOrder = {"bankList"})
public class JAXBBanks {

    @XmlElement(name = "bank")
    private final List<JAXBBank> bankList = new ArrayList<>();

    public List<JAXBBank> getBanks() {
        return bankList;
    }
}