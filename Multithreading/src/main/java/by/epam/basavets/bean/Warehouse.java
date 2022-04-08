package by.epam.basavets.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Warehouse {

    private final int MAX_SIZE = 100;
    private List<Container> warehouseContainers;



    public Warehouse(){
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
}
