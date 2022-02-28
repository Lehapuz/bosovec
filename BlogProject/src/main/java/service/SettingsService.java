package service;

import bean.Enum.SettingStatus;
import bean.Settings;
import dao.SettingsDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SettingsService {


    Settings settings = new Settings();
    private final SettingsDAO settingsDAO;
    private final Logger logger = LogManager.getRootLogger();

    public SettingsService(SettingsDAO settingsDAO) {
        this.settingsDAO = settingsDAO;
    }


    public void setSettings(String status) {
        if (status.equals("Yes")) {
            settings.setSettingStatus(SettingStatus.Yes);
            settingsDAO.addSettings(settings);
            logger.info("Доступ разрешен");
        }
        if (status.equals("No")) {
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
