package by.epam.basavets.bean;

import java.io.Serializable;
import java.util.Objects;

public class Settings implements Serializable {

    private SettingStatus settingStatus;
    private static final long serialVersionUID = 1177L;

    public Settings() {
    }

    public SettingStatus getSettingStatus() {
        return settingStatus;
    }

    public void setSettingStatus(SettingStatus settingStatus) {
        this.settingStatus = settingStatus;
    }

    @Override
    public int hashCode() {
        return settingStatus == null ? 0 : Objects.hash(settingStatus);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Settings settings = (Settings) obj;
        return Objects.equals(settingStatus, settings.settingStatus);
    }

    @Override
    public String toString() {
        return settingStatus.toString();
    }
}
