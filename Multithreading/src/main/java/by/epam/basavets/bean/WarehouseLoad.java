package by.epam.basavets.bean;

public class WarehouseLoad implements Runnable{

    private Port port;

    public WarehouseLoad(Port port){
        this.port = port;
    }

    @Override
    public void run() {
        while (true){
            port.warehouseLoader();
        }

    }
}
