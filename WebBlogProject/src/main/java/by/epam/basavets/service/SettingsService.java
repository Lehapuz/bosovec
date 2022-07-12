package by.epam.basavets.service;

import by.epam.basavets.bean.SettingStatus;

public interface SettingsService {

    void setSettings(String status);

    SettingStatus showSettings();
}
