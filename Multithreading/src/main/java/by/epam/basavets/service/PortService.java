package by.epam.basavets.service;

import by.epam.basavets.bean.Container;
import by.epam.basavets.bean.Ship;
import by.epam.basavets.bean.Warehouse;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class PortService {
    private final int JETTY_COUNT = 5;
    private List<Ship> ships;
    private int shipCount = 0;
    private int shipId = 0;
    private final Warehouse warehouse = new Warehouse();
    //private ReentrantLock locker = new ReentrantLock();
    //private Condition condition = locker.newCondition();

    //Semaphore semaphore;


    public PortService() {
        ships = new CopyOnWriteArrayList<>();
    }

    public synchronized void add(Ship ship) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (shipCount < JETTY_COUNT) {
            ships.add(ship);
            ship.setId(++shipId);
            ++shipCount;
            System.out.println("Корабль - " + ship.getId() + " зашел в порт - " + ship.isProcessed());
            System.out.println("Число контейнеров на корабле - " + ship.getShipContainers().size());
        }
    }


    public synchronized void delete() {
        if (shipCount == JETTY_COUNT) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Ship ship = ships.get(shipCount - JETTY_COUNT);
            if (ship.isProcessed()) {
                shipCount--;
                ships.remove(ship);
                System.out.println("Корабль - " + ship.getId() + " вышел из порта" + " Статус - " + ship.isProcessed());
            }
        }
    }


    public synchronized void warehouseLoader() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (ships.size() != 0) {
            for (Ship ship : ships) {
                if (!ship.isProcessed() && !ship.isUnloaded()) {
                    for (Container container : ship.getShipContainers()) {

                        ship.getShipContainers().remove(container);
                        warehouse.getWarehouseContainers().add(container);
                    }

                    System.out.println("Корабль разгружен - " + ship.getId());
                    System.out.println("Число контейнеров на корабле - " + ship.getShipContainers().size());
                    System.out.println("Число контейнеров на складе - " + warehouse.getWarehouseContainers().size());
                }

                if (ship.getShipContainers().size() == 0) {
                    ship.setUnloaded(true);
                }

                if (warehouse.getWarehouseContainers().size() > warehouse.getMAX_SIZE()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public synchronized void shipLoader() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Ship ship : ships) {

            if (warehouse.getWarehouseContainers().size() <= warehouse.getMAX_SIZE()) {
                notifyAll();
            }

            if (warehouse.getWarehouseContainers().size() > ship.getMAX_CONTAINERS_SIZE()
                    && ship.isUnloaded() && !ship.isProcessed()) {
                for (Container container : warehouse.getWarehouseContainers()) {
                    warehouse.getWarehouseContainers().remove(container);
                    ship.getShipContainers().add(container);
                    if (ship.getShipContainers().size() == ship.getMAX_CONTAINERS_SIZE()) {
                        break;
                    }
                }

                System.out.println("Корабль загружен - " + ship.getId());
                System.out.println("Число контейнеров загруженных на корабль - " + ship.getShipContainers().size());
                System.out.println("Число контейнеров оставвшихся на складе - " + warehouse.getWarehouseContainers().size());

                if (ship.getShipContainers().size() == ship.getMAX_CONTAINERS_SIZE()) {
                    ship.setProcessed(true);
                }
            }
        }
    }
}
