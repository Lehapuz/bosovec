package by.epam.basavets.servise;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JAXBParseFile {

    public JAXBBank parseFile(String pathFile) throws JAXBException, FileNotFoundException {
        //JAXBContext context = JAXBContext.newInstance(JAXBDepositor.class);

        //Unmarshaller unmarshaller = context.createUnmarshaller();
        //JAXBDepositor jaxbDepositor = (JAXBDepositor) unmarshaller.unmarshal(new File(pathFile));



        JAXBContext context = JAXBContext.newInstance(JAXBBank.class);
        JAXBBank jaxbBank = (JAXBBank) context.createUnmarshaller().unmarshal(new FileReader(pathFile));
System.out.println(jaxbBank.getId());
        return jaxbBank;



    }
}
