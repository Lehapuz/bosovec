package by.epam.basavets.flow;

import by.epam.basavets.service.PortService;

public class WarehouseLoad implements Runnable {

    private final PortService port;

    public WarehouseLoad(PortService port) {
        this.port = port;
    }

    @Override
    public void run() {
        while (true) {
            port.warehouseLoader();
        }
    }
}
