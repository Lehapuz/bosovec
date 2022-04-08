package by.epam.basavets.flow;

import by.epam.basavets.service.PortService;

public class ShipLoad implements Runnable {

    private final PortService port;

    public ShipLoad(PortService port) {
        this.port = port;
    }

    @Override
    public void run() {
        while (true) {
            port.shipLoader();
        }
    }
}
