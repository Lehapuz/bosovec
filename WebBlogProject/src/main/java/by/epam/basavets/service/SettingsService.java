package by.epam.basavets.service;

import by.epam.basavets.bean.Enum.SettingStatus;
import by.epam.basavets.bean.Settings;
import by.epam.basavets.dao.impl.SettingsDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class SettingsService {

    Settings settings = new Settings();
    private final SettingsDAO settingsDAO;
    private final Logger logger = LogManager.getRootLogger();
    private final String YES = "Yes";
    private final String NO = "No";


    public SettingsService(SettingsDAO settingsDAO) {
        this.settingsDAO = settingsDAO;
    }


    public void setSettings(String status) throws SQLException {
        settingsDAO.deleteSettings();
        if (status.equals(YES)) {
            settings.setSettingStatus(SettingStatus.Yes);
            settingsDAO.addSettings(settings);
            logger.info("Access is allowed");
        }
        if (status.equals(NO)) {
            settings.setSettingStatus(SettingStatus.No);
            settingsDAO.addSettings(settings);
            logger.info("Access is denied");
        }
    }


    public SettingStatus showSettings() throws SQLException {
        if (settingsDAO.getSettings() == null) {
            settings.setSettingStatus(SettingStatus.No);
            settingsDAO.addSettings(settings);
        }
        logger.info("Status - " + settingsDAO.getSettings());
        return settingsDAO.getSettings();
    }
}
