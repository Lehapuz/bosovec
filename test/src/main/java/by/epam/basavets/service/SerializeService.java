package by.epam.basavets.service;


import by.epam.basavets.dao.DataSource;

import java.io.*;

public class SerializeService {

    private final DataSource dataSource;
    private final String SAVE_FILE = "src/main/resources/save.ser";

    public SerializeService(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void writeFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(SAVE_FILE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(dataSource);
        objectOutputStream.close();
    }


    public void readFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(SAVE_FILE);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        DataSource dataSource = (DataSource) objectInputStream.readObject();

        //dataSource.getModeratorDAO().read();
        //dataSource.getUserDAO().read();
        //System.out.println(dataSource.getPostDAO().read());
        //dataSource.getPostCommentDAO().read();
        objectInputStream.close();
    }
}
