package by.epam.basavets.flow;

import by.epam.basavets.service.PortService;

public class WarehouseLoader implements Runnable {

    private final PortService port;

    public WarehouseLoader(PortService port) {
        this.port = port;
    }

    @Override
    public void run() {
        while (true) {
            port.warehouseLoader();
        }
    }
}
