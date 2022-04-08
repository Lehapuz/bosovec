package by.epam.basavets.bean;

public class ShipLoad implements Runnable{

    private Port port;

    public ShipLoad(Port port){
        this.port = port;
    }

    @Override
    public void run() {
        while (true){

            port.shipLoader();


        }
    }
}
