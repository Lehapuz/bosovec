package by.epam.basavets.bean;

import by.epam.basavets.bean.Enum.SettingStatus;

public class Settings {

    private SettingStatus settingStatus;

    public Settings() {
    }

    public SettingStatus getSettingStatus() {
        return settingStatus;
    }

    public void setSettingStatus(SettingStatus settingStatus) {
        this.settingStatus = settingStatus;
    }
}
