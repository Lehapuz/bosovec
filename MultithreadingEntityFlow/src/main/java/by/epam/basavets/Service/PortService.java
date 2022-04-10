package by.epam.basavets.Service;

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
    private List<Ship> ships;
    private final int JETTY_COUNT = 5;
    private int shipCount = 0;
    private int shipID = 0;
    private final ReentrantLock locker = new ReentrantLock();
    private final Condition condition = locker.newCondition();
    private final Logger logger = LogManager.getRootLogger();

    public PortService() {
        ships = new CopyOnWriteArrayList<>();
    }


    public void shipIn(Ship ship) {
        locker.lock();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (shipCount >= JETTY_COUNT) {
            logger.info("Порт заполнен");
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < ship.getMAX_CONTAINERS_SIZE(); i++) {
            Container container = new Container();
            ship.getShipContainers().add(container);
        }
        shipCount++;
        shipID++;
        ship.setId(shipID);
        logger.info("Корабль - " + ship.getId() + " зашел в порт "
                + " Количество контейнеров - " + ship.getShipContainers().size());
        logger.info(shipCount);
        ships.add(ship);
        locker.unlock();
    }


    public void warehouseLoader(Warehouse warehouse) {
        locker.lock();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (shipCount < JETTY_COUNT) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (shipCount >= JETTY_COUNT) {
            condition.signalAll();
        }

        for (Ship ship : ships) {

            if (!ship.isProcessed() && !ship.isUnloaded()) {
                if (warehouse.getWarehouseContainers().size() == warehouse.getMAX_SIZE()) {
                    logger.info("Склад заполнен");
                    break;
                }
                for (Container container : ship.getShipContainers()) {
                    ship.getShipContainers().remove(container);
                    warehouse.getWarehouseContainers().add(container);
                }

                logger.info("Корабль - " + ship.getId() + " разгружен");
                logger.info("Число контейнеров на корабле - " + ship.getShipContainers().size());
                logger.info("Число контейнеров на складе - " + warehouse.getWarehouseContainers().size());
                ship.setUnloaded(true);
            }
        }

        for (Ship ship : ships) {
            if (!ship.isProcessed() && ship.isUnloaded()) {
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

        for (Ship ship : ships) {
            if (ship.isProcessed()) {
                ships.remove(ship);
                shipCount--;
                logger.info("Корабль - " + ship.getId() + " покинул порт");
                logger.info(shipCount);
            }
        }
        locker.unlock();
    }
}


