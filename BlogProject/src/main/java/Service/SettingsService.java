package Service;

import Bean.Enum.SettingStatus;
import Bean.Settings;
import DAO.SettingsDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class SettingsService {


    Settings settings = new Settings();
    private final SettingsDAO settingsDAO;
    Scanner scanner = new Scanner(System.in);
    private final Logger logger = LogManager.getRootLogger();

    public SettingsService(SettingsDAO settingsDAO) {
        this.settingsDAO = settingsDAO;
    }


    public void setSettings() {
        logger.info("Разрешить или запретить доступ");
        String input = scanner.nextLine();
        if (input.equals("Разрешить")) {
            settings.setSettingStatus(SettingStatus.Yes);
            settingsDAO.addSettings(settings);
            logger.info("Доступ разрешен");
        }
        if (input.equals("Запретить")) {
            settings.setSettingStatus(SettingStatus.No);
            settingsDAO.addSettings(settings);
            logger.info("Доступ запрещен");
        }
    }

    public void showSettings() {
        if (settings.getSettingStatus() == null) {
            settings.setSettingStatus(SettingStatus.Yes);
            settingsDAO.addSettings(settings);
        }
        logger.info("Статус добавления постов - " + settings.getSettingStatus());
    }
}
