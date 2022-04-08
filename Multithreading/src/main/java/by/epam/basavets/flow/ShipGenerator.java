package by.epam.basavets.flow;

import by.epam.basavets.bean.Container;
import by.epam.basavets.bean.Ship;
import by.epam.basavets.service.PortService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShipGenerator implements Runnable {

    private final PortService port;

    public ShipGenerator(PortService port) {
        this.port = port;
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
        }
    }
}
