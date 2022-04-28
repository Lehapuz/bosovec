package by.epam.basavets.dao;

import by.epam.basavets.utils.DBConnection;
import by.epam.basavets.bean.Enum.SettingStatus;
import by.epam.basavets.bean.Settings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingsDAO {


    public void addSettings(Settings settings) throws SQLException {
        String sql = "INSERT INTO settings (setting_status) " +
                "VALUES (?)";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, settings.getSettingStatus().toString());
        statement.execute();
        statement.close();
        connection.close();
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
            statement1.close();
            connection1.close();
        }
        resultSet.close();
        statement.close();
        connection.close();
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
        resultSet.close();
        statement.close();
        connection.close();
        return settings.getSettingStatus();
    }
}
