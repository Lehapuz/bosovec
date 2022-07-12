package by.epam.basavets.dao.impl;

import by.epam.basavets.dao.ConnectionPool;
import by.epam.basavets.dao.DAOException;
import by.epam.basavets.bean.SettingStatus;
import by.epam.basavets.bean.Settings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SettingsDAO implements by.epam.basavets.dao.SettingsDAO {

    private final Logger logger = LogManager.getRootLogger();


    @Override
    public void addSettings(Settings settings) {
        String sql = "INSERT INTO settings (setting_status) " +
                "VALUES (?)";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, settings.getSettingStatus().toString());
            statement.execute();
            ConnectionPool.getInstance().givenAwayConnection(connection, statement);
        } catch (Exception e) {
            logger.error("Can not add setting");
            throw new DAOException("Can not add setting", e);
        }
    }


    @Override
    public void deleteSettings() {
        String sql = "SELECT * FROM settings";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Settings settings = new Settings();
                settings.setSettingStatus(SettingStatus.valueOf(resultSet.getString("setting_status")));
                String sql1 = "DELETE FROM settings WHERE setting_status = ?";
                Connection connection1 = ConnectionPool.getInstance().takeConnection();
                PreparedStatement statement1 = connection1.prepareStatement(sql1);
                statement1.setString(1, settings.getSettingStatus().toString());
                statement1.execute();
                ConnectionPool.getInstance().givenAwayConnection(connection, statement, resultSet);
                ConnectionPool.getInstance().givenAwayConnection(connection1, statement1);
            }
        } catch (Exception e) {
            logger.error("Can not delete setting");
            throw new DAOException("Can not delete setting", e);
        }
    }


    @Override
    public SettingStatus getSettings() {
        String sql = "SELECT * FROM settings";
        Settings settings = new Settings();
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                settings.setSettingStatus(SettingStatus.valueOf(resultSet.getString("setting_status")));
            }
            ConnectionPool.getInstance().givenAwayConnection(connection, statement, resultSet);
        } catch (Exception e) {
            logger.error("Can not delete setting");
            throw new DAOException("Can not delete setting", e);
        }
        return settings.getSettingStatus();
    }
}
