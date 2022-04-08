package by.epam.basavets.bean;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private final int JETTY_COUNT = 5;
    private List<Ship> ships;
    private int shipCount = 1;
    private int shipId = 1;
    private final Warehouse warehouse = new Warehouse();
    private ReentrantLock locker = new ReentrantLock();
    private Condition condition = locker.newCondition();
    //Semaphore semaphore;




    public Port() {
        ships = new CopyOnWriteArrayList<>();
        //this.semaphore = semaphore;
    }

    public synchronized void add(Ship ship) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (shipCount <= JETTY_COUNT){
            //notifyAll();


            ship.setId(shipId++);
            shipCount++;
            ships.add(ship);
            System.out.println("Корабль - " + ship.getId() +  " зашел в порт - " + ship.isProcessed());
            System.out.println("Число контейнеров на корабле - " + ship.getShipContainers().size());
        }


        if (shipCount > JETTY_COUNT) {
            try {
            //locker.lock();
            wait();
            //semaphore.acquire();
            //condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       }
       //else {


            //locker.unlock();

            //semaphore.release();

            //condition.signal();
        //}
    }


    public synchronized void delete() {
        if (shipCount == JETTY_COUNT){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //locker.unlock();
            //notify();
            //condition.signal();



            Ship ship = ships.get(shipCount - JETTY_COUNT);
            if (ship.isProcessed()){
                shipCount--;
                ships.remove(ship);
                System.out.println("Корабль - " + ship.getId() +  " вышел из порта" + " Статус - " + ship.isProcessed());
            }

            }
        else {
//            try {
//                //locker.lock();
//                wait();
//                //condition.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }


        public synchronized void warehouseLoader(){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (ships.size() != 0) {



                for (Ship ship : ships){
                    if (!ship.isProcessed()){
                        for (int i = 0; i < ship.getShipContainers().size(); i++) {


                                Container container = ship.getShipContainers().get(i);
                                ship.getShipContainers().remove(container);
                                warehouse.getWarehouseContainers().add(container);





                            if (warehouse.getWarehouseContainers().size() > warehouse.getMAX_SIZE()) {
                                try {
                                    wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            else {
                                notify();
                            }
                    }

                        System.out.println("Корабль разгружен - " + ship.getId());
                        System.out.println("Число контейнеров на корабле - " + ship.getShipContainers().size());
                        System.out.println("Число контейнеров на складе - " + warehouse.getWarehouseContainers().size());
                        System.out.println(ship.isProcessed());
                    }

                    if (ship.getShipContainers().size() == 0){
                        ship.setUnloaded(true);
                        System.out.println("Корабль полностью разгружен" + ship.isUnloaded());
                        //try {
                        //    wait();
                        //} catch (InterruptedException e) {
                        //    e.printStackTrace();
                        //}

                    }
                }
            }




            //if (shipCount > 0 && warehouse.getWarehouseContainers().size() <= warehouse.getMAX_SIZE()){
                //locker.unlock();
                //notify();
                //condition.signal();

                //for (Ship ship : ships){
                    //if (ship.getShipContainers().size() != 0 && !ship.isProcessed()){
                        //for (int i = 0; i < ship.getShipContainers().size(); i++) {

                            //if (warehouse.getWarehouseContainers().size() < warehouse.getMAX_SIZE()){
                                //locker.unlock();
                                //notify();
                                //condition.signal();
                                //System.out.println(ship.getShipContainers().size());
                                //Container container = new Container();
                                //Container container = ship.getShipContainers().get(i);
                                //ship.getShipContainers().remove(container);
                                //warehouse.getWarehouseContainers().add(container);
                            }

//                            else {
//                                try {
//                                    //locker.lock();
//                                    wait();
//                                    //condition.await();
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
                    //}


                    //ship.getShipContainers().clear();
//                    System.out.println("Корабль разгружен - " + ship.getId());
//                    System.out.println("Число контейнеров на корабле - " + ship.getShipContainers().size());
//                    System.out.println("Число контейнеров на складе - " + warehouse.getWarehouseContainers().size());
//                    System.out.println(ship.isProcessed());
                //}


            //}
//            else {
//                try {
//                    //locker.lock();
//                    wait();
//                    //condition.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
                //}
            //}
    //}



            synchronized public void shipLoader(){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                    for (Ship ship : ships) {

                        if (warehouse.getWarehouseContainers().size() >= ship.getMAX_CONTAINERS_SIZE()
                                && ship.isUnloaded()){

                        for (int i = 0; i < ship.getMAX_CONTAINERS_SIZE(); i++) {

                                Container container = warehouse.getWarehouseContainers().get(i);
                                warehouse.getWarehouseContainers().remove(container);
                                ship.getShipContainers().add(container);
                            }

                            System.out.println("Корабль загружен - " + ship.getId());
                            System.out.println("Число контейнеров загруженных на корабль - " + ship.getShipContainers().size());
                            System.out.println("Число контейнеров оставвшихся на складе - " + warehouse.getWarehouseContainers().size());
                            System.out.println(ship.isProcessed());

                            if (ship.getShipContainers().size() == ship.getMAX_CONTAINERS_SIZE()){
                                ship.setProcessed(true);
                            }
                    }




                        if (ship.getShipContainers().size() != 0){
                            try {
                                wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            notify();
                        }
                }








                //if (shipCount > 0 && warehouse.getWarehouseContainers().size() > 0){

                    //for (Ship ship : ships){

                        //if (ship.getShipContainers().size() == 0){
                            //locker.unlock();
                            //notify();
                            //condition.signal();
                            //for (int i = 0; i < ship.getMAX_CONTAINERS_SIZE(); i++) {
                                //if (warehouse.getWarehouseContainers().size() >= ship.getMAX_CONTAINERS_SIZE()){
                                    //locker.unlock();
                                    //notify();
                                    //condition.signal();

                                    //Container container = new Container();
                                    //Container container = warehouse.getWarehouseContainers().get(i);
                                    //warehouse.getWarehouseContainers().remove(container);
                                    //ship.getShipContainers().add(container);


//                                }
//                                else {
//                                    try {
//                                    //locker.lock();
//                                        wait();
//                                        //condition.await();
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
//
//
//                                }
//
//
//
//                            }
                            //ship.setProcessed(true);

//                        }
//

//                        System.out.println("Корабль загружен - " + ship.getId());
//                        System.out.println("Число контейнеров загруженных на корабль - " + ship.getShipContainers().size());
//                        System.out.println("Число контейнеров оставвшихся на складе - " + warehouse.getWarehouseContainers().size());
////                        System.out.println(ship.isProcessed());

//
                }
//                else {
//                    try {
//                        //locker.lock();
//                        wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                    //}
               //}
            //}



    public Ship getShip (int shipId){
        for (Ship ship : ships){
            if (ship.getId() == shipId){
                return ship;
            }
        }
        return null;
    }

    public int getShipId(){
        return shipId;
    }

    public int getJETTY_COUNT(){
        return JETTY_COUNT;
    }


    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

//    public int getShipNumber() {
//        return shipNumber;
//    }
//
//    public void setShipNumber(int shipNumber) {
//        this.shipNumber = shipNumber;
//    }
}
