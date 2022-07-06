package by.epam.basavets.dao;

import by.epam.basavets.DBConnection;
import by.epam.basavets.bean.Enum.SettingStatus;
import by.epam.basavets.bean.Settings;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class SettingsDAO implements Serializable {

    private final Set<Settings> settingsSet = new HashSet<>();
    private static final long serialVersionUID = 16L;


    public void addSettings(Settings settings) throws SQLException {
        String sql = "INSERT INTO settings (setting_status) " +
                "VALUES (?)";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement2 = connection.prepareStatement(sql);
        statement2.setString(1, settings.getSettingStatus().toString());
        statement2.execute();
    }


    public void deleteSettings() throws SQLException {
        String sql = "SELECT * FROM settings";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Settings settings = new Settings();
            settings.setSettingStatus(SettingStatus.valueOf(resultSet.getString("setting_status")));
            String sql1 = "DELETE FROM settings WHERE setting_status = ?";
            Connection connection1 = DBConnection.getConnection();
            PreparedStatement statement1 = connection1.prepareStatement(sql1);
            statement1.setString(1, settings.getSettingStatus().toString());
            statement1.execute();
        }
    }


    public SettingStatus getSettings() throws SQLException {
        String sql = "SELECT * FROM settings";
        Settings settings = new Settings();
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            settings.setSettingStatus(SettingStatus.valueOf(resultSet.getString("setting_status")));
        }
        return settings.getSettingStatus();
    }
}
