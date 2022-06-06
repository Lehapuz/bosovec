package by.epam.basavets.bean;

import by.epam.basavets.bean.Enum.SettingStatus;

import java.io.Serializable;

public class Settings implements Serializable {

    private SettingStatus settingStatus;
    private static final long serialVersionUID = 6L;

    public Settings() {
    }

    public SettingStatus getSettingStatus() {
        return settingStatus;
    }

    public void setSettingStatus(SettingStatus settingStatus) {
        this.settingStatus = settingStatus;
    }
}
