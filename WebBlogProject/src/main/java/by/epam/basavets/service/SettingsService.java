package by.epam.basavets.service;

import by.epam.basavets.bean.Enum.SettingStatus;
import by.epam.basavets.bean.Settings;
import by.epam.basavets.dao.SettingsDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class SettingsService {

    Settings settings = new Settings();
    private final SettingsDAO settingsDAO;
    private final Logger logger = LogManager.getRootLogger();


    public SettingsService(SettingsDAO settingsDAO) {
        this.settingsDAO = settingsDAO;
    }


    public void setSettings(String status) throws SQLException {
        settingsDAO.deleteSettings();
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


    public SettingStatus showSettings() throws SQLException {
        if (settingsDAO.getSettings() == null) {
            settings.setSettingStatus(SettingStatus.No);
            settingsDAO.addSettings(settings);
        }
        logger.info("Статус добавления постов - " + settingsDAO.getSettings());
        return settingsDAO.getSettings();
    }
}
