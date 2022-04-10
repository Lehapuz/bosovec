package by.epam.basavets.service;

import by.epam.basavets.bean.Container;
import by.epam.basavets.bean.Ship;
import by.epam.basavets.bean.Warehouse;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PortService {
    private final int JETTY_COUNT = 5;
    private List<Ship> ships;
    private int shipCount = 0;
    private int shipId = 0;
    private final Warehouse warehouse = new Warehouse();
    private final ReentrantLock locker = new ReentrantLock();
    private final Condition condition = locker.newCondition();


    public PortService() {
        ships = new CopyOnWriteArrayList<>();
    }


    public void add(Ship ship) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (shipCount < JETTY_COUNT) {
            locker.lock();
            ships.add(ship);
            ship.setId(++shipId);
            ++shipCount;
            System.out.println("Корабль - " + ship.getId() + " зашел в порт - " + ship.isProcessed());
            System.out.println("Число контейнеров на корабле - " + ship.getShipContainers().size());
            locker.unlock();
        }
    }


    public void delete() {
        locker.lock();
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
        locker.unlock();
    }


    public void warehouseLoader() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (ships.size() != 0) {
            locker.lock();
            for (Ship ship : ships) {

                if (!ship.isProcessed() && !ship.isUnloaded()) {
                    for (Container container : ship.getShipContainers()) {
                        ship.getShipContainers().remove(container);
                        warehouse.getWarehouseContainers().add(container);
                    }

                    if (warehouse.getWarehouseContainers().size() > warehouse.getMAX_SIZE()) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println("Корабль - " + ship.getId() + " разгружен");
                    System.out.println("Число контейнеров на корабле - " + ship.getShipContainers().size());
                    System.out.println("Число контейнеров на складе - " + warehouse.getWarehouseContainers().size());
                }

                if (ship.getShipContainers().size() == 0) {
                    ship.setUnloaded(true);
                }
            }
            locker.unlock();
        }
    }


    public void shipLoader() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        locker.lock();
        for (Ship ship : ships) {

            if (warehouse.getWarehouseContainers().size() <= warehouse.getMAX_SIZE()) {
                condition.signalAll();
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

                System.out.println("Корабль - " + ship.getId() + " зазгружен");
                System.out.println("Число контейнеров загруженных на корабль - " + ship.getShipContainers().size());
                System.out.println("Число контейнеров оставвшихся на складе - " + warehouse.getWarehouseContainers().size());

                if (ship.getShipContainers().size() == ship.getMAX_CONTAINERS_SIZE()) {
                    ship.setProcessed(true);
                }
            }
        }
        locker.unlock();
    }
}
