package by.epam.basavets.flow;

import by.epam.basavets.service.PortService;

public class ShipDelete implements Runnable {
    private final PortService port;

    public ShipDelete(PortService port) {
        this.port = port;
    }

    @Override
    public void run() {
        while (true) {
            port.delete();
        }
    }
}
