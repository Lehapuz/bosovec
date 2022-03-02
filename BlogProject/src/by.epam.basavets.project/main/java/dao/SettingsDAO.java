package dao;

import bean.Enum.SettingStatus;
import bean.Settings;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SettingsDAO implements Serializable {

    private final Set<Settings> settingsSet = new HashSet<>();
    private static final long serialVersionUID = 16L;

    public void addSettings(Settings settings) {
        settingsSet.clear();
        settingsSet.add(settings);
    }

    public SettingStatus getSettings() {
        for (Settings settings : settingsSet) {
            return settings.getSettingStatus();
        }
        return null;
    }

    public Set<Settings> getSettingsSet() {
        return settingsSet;
    }
}
