package by.epam.basavets.service;

import by.epam.basavets.bean.Container;
import by.epam.basavets.bean.Ship;
import by.epam.basavets.bean.Warehouse;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PortService {
    private final int JETTY_COUNT = 5;
    private List<Ship> ships;
    private int shipCount = 0;
    private int shipId = 0;
    private final Warehouse warehouse = new Warehouse();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private final Logger logger = LogManager.getRootLogger();


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
            lock.lock();
            ships.add(ship);
            ship.setId(++shipId);
            ++shipCount;
            logger.info("Корабль - " + ship.getId() + " зашел в порт - " + ship.isProcessed());
            logger.info("Число контейнеров на корабле - " + ship.getShipContainers().size());
            lock.unlock();
        }
    }


    public void delete() {
        lock.lock();
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
                logger.info("Корабль - " + ship.getId() + " вышел из порта" + " Статус - " + ship.isProcessed());
            }
        }
        lock.unlock();
    }


    public void warehouseLoader() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (ships.size() != 0) {
            lock.lock();
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

                    logger.info("Корабль - " + ship.getId() + " разгружен");
                    logger.info("Число контейнеров на корабле - " + ship.getShipContainers().size());
                    logger.info("Число контейнеров на складе - " + warehouse.getWarehouseContainers().size());
                }

                if (ship.getShipContainers().size() == 0) {
                    ship.setUnloaded(true);
                }
            }
            lock.unlock();
        }
    }


    public void shipLoader() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.lock();
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

                logger.info("Корабль - " + ship.getId() + " загружен");
                logger.info("Число контейнеров загруженных на корабль - " + ship.getShipContainers().size());
                logger.info("Число контейнеров оставвшихся на складе - " + warehouse.getWarehouseContainers().size());

                if (ship.getShipContainers().size() == ship.getMAX_CONTAINERS_SIZE()) {
                    ship.setProcessed(true);
                }
            }
        }
        lock.unlock();
    }
}
