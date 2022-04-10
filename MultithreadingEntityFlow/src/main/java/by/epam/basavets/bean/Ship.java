package by.epam.basavets.bean;

import by.epam.basavets.Service.PortService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Ship implements Runnable {

    private int id;
    private final int MAX_CONTAINERS_SIZE = 20;
    private List<Container> shipContainers;
    private boolean processed;
    private boolean unloaded;
    private final PortService portService;


    public Ship(PortService portService) {
        this.portService = portService;
        shipContainers = new CopyOnWriteArrayList<>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMAX_CONTAINERS_SIZE() {
        return MAX_CONTAINERS_SIZE;
    }

    public List<Container> getShipContainers() {
        return shipContainers;
    }

    public void setShipContainers(List<Container> shipContainers) {
        this.shipContainers = shipContainers;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isUnloaded() {
        return unloaded;
    }

    public void setUnloaded(boolean unloaded) {
        this.unloaded = unloaded;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public void run() {
        portService.shipIn(this);
    }
}
