package by.epam.basavets.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class ShipGenerator implements Runnable{

       private Port port;
    private int shipId = 1;
    private int shipCount = 0;
    private ReentrantLock locker = new ReentrantLock();
    private List<Ship>ships;

    public ShipGenerator(Port port){
        this.port = port;
        ships = new CopyOnWriteArrayList<>();
    }



    @Override
    public void run() {


        while (true) {

            Ship ship = new Ship();
            List<Container> containers = new CopyOnWriteArrayList<>();
            for (int i = 0; i < ship.getMAX_CONTAINERS_SIZE(); i++) {
                Container container = new Container();
                containers.add(container);
            }
            ship.setShipContainers(containers);

            port.add(ship);
            //port.warehouseLoader();
            //port.shipLoader();
            //port.delete();


        }
    }
}
