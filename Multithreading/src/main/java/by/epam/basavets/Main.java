package by.epam.basavets;

import by.epam.basavets.flow.ShipDelete;
import by.epam.basavets.flow.ShipGenerator;
import by.epam.basavets.flow.ShipLoad;
import by.epam.basavets.flow.WarehouseLoad;
import by.epam.basavets.service.PortService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        PortService port = new PortService();
        ShipGenerator shipGenerator = new ShipGenerator(port);
        ShipDelete shipDelete = new ShipDelete(port);
        WarehouseLoad warehouseLoad = new WarehouseLoad(port);
        ShipLoad shipLoad = new ShipLoad(port);

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors);

        ExecutorService service = Executors.newFixedThreadPool(processors);
        service.execute(shipGenerator);
        service.execute(warehouseLoad);
        service.execute(shipLoad);
        service.execute(shipDelete);
        service.shutdown();
        System.out.println(service.toString());
    }
}