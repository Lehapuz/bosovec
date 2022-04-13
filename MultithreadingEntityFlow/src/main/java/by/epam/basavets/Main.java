package by.epam.basavets;

import by.epam.basavets.Service.FileRead;
import by.epam.basavets.Service.PortService;
import by.epam.basavets.bean.Ship;
import by.epam.basavets.bean.Warehouse;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newCachedThreadPool();
        PortService portService = new PortService();
        Warehouse warehouse = new Warehouse(portService);
        String path = "src/main/resources/text.txt";
        FileRead fileRead = new FileRead();

        for (int i = 1; i <= fileRead.getShipCount(path); i++) {
            Ship ship = new Ship(portService);
            service.execute(ship);
            service.execute(warehouse);
        }
        service.shutdown();
        System.out.println(service.toString());
    }
}

