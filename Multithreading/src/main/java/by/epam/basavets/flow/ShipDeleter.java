package by.epam.basavets.flow;

import by.epam.basavets.service.PortService;

public class ShipDeleter implements Runnable {
    private final PortService port;

    public ShipDeleter(PortService port) {
        this.port = port;
    }

    @Override
    public void run() {
        while (true) {
            port.delete();
        }
    }
}
