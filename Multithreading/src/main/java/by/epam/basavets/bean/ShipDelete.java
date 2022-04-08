package by.epam.basavets.bean;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShipDelete implements Runnable{
    private Port port;
    private ShipGenerator shipGenerator;
    private int count = 0;
    //List<Ship> ships = port.getShips();
    //private int shipCount = ships.size();



    public ShipDelete(Port port){
        this.port = port;
    }



    //private final Port port = new Port();

    @Override
    public void run() {
        while (true){


            port.delete();

        }

    }


}
