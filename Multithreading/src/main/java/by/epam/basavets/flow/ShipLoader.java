package by.epam.basavets.flow;

import by.epam.basavets.service.PortService;

public class ShipLoader implements Runnable {

    private final PortService port;

    public ShipLoader(PortService port) {
        this.port = port;
    }

    @Override
    public void run() {
        while (true) {
            port.shipLoader();
        }
    }
}
