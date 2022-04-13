package by.epam.basavets;

import by.epam.basavets.flow.ShipDeleter;
import by.epam.basavets.flow.ShipGenerator;
import by.epam.basavets.flow.ShipLoader;
import by.epam.basavets.flow.WarehouseLoader;
import by.epam.basavets.service.PortService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        PortService port = new PortService();
        ShipGenerator shipGenerator = new ShipGenerator(port);
        ShipDeleter shipDeleter = new ShipDeleter(port);
        WarehouseLoader warehouseLoader = new WarehouseLoader(port);
        ShipLoader shipLoader = new ShipLoader(port);

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors);

        ExecutorService service = Executors.newFixedThreadPool(processors);
        service.execute(shipGenerator);
        service.execute(warehouseLoader);
        service.execute(shipLoader);
        service.execute(shipDeleter);
        service.shutdown();
        System.out.println(service.toString());
    }
}