package by.epam.basavets;

import by.epam.basavets.bean.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main (String[] args) {
        System.out.println("Привет");
//        Ship ship1 = new Ship();
        Semaphore semaphore = new Semaphore(4, true);
        Port port = new Port();
//        Ship ship2 = new Ship();
//        Ship ship3 = new Ship();
//        Ship ship4 = new Ship();
//        Ship ship5= new Ship();
//        Ship ship6 = new Ship();

        ShipGenerator shipGenerator = new ShipGenerator(port);
        ShipDelete shipDelete = new ShipDelete(port);
        WarehouseLoad warehouseLoad = new WarehouseLoad(port);
        ShipLoad shipLoad = new ShipLoad(port);
        //Thread thread = new Thread(shipGenerator);
        //Thread thread1 = new Thread(shipDelete);
//
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors);
        ExecutorService service = Executors.newFixedThreadPool(processors);
        service.execute(shipGenerator);
        //service.execute(shipGenerator);
        //service.execute(shipGenerator);
        //service.execute(shipGenerator);
        service.execute(shipDelete);
        service.execute(warehouseLoad);
        service.execute(shipLoad);


//        //service.execute(port);
//        //service.execute(ship3);
//        //service.execute(ship4);
//        //service.execute(ship5);
//        //service.execute(ship6);
        service.shutdown();
        //thread.start();
        //thread1.start();
//
        System.out.println(service.toString());
    }
}