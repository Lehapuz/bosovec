package by.epam.basavets.dao;

import by.epam.basavets.bean.SettingStatus;
import by.epam.basavets.bean.Settings;

public interface SettingsDAO {

    void addSettings(Settings settings);

    void deleteSettings();

    SettingStatus getSettings();
}
