package by.epam.basavets.bean;

import by.epam.basavets.Service.PortService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Warehouse implements Runnable{

    private final int MAX_SIZE = 60;
    private List<Container> warehouseContainers;
    private final PortService portService;


    public Warehouse(PortService portService) {
        this.portService = portService;
        warehouseContainers = new CopyOnWriteArrayList<>();
    }

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }

    public List<Container> getWarehouseContainers() {
        return warehouseContainers;
    }

    public void setWarehouseContainers(List<Container> warehouseContainers) {
        this.warehouseContainers = warehouseContainers;
    }

    @Override
    public void run() {
        portService.warehouseLoader(this);
    }
}
