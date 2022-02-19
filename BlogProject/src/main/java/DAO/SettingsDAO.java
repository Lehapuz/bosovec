package DAO;

import Bean.Enum.SettingStatus;
import Bean.Settings;

import java.util.HashSet;
import java.util.Set;

public class SettingsDAO {

    private final Set<Settings> settingsSet = new HashSet<>();

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
