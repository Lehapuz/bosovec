package by.epam.basavets.bean;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Ship {

    private int id;
    private final int MAX_CONTAINERS_SIZE = 20;
    private List<Container> shipContainers;
    private boolean processed = false;
    private boolean unloaded = false;


    public Ship() {
        shipContainers = new CopyOnWriteArrayList<>();
    }


    public void setShipContainers(List<Container> shipContainers) {
        this.shipContainers = shipContainers;
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
}
