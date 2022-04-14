import java.sql.*;

public class DatabaseConnection {

    public void DataBaseConnection(String url, String user, String pass) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT course_name, COUNT(MONTH(subscription_date))" +
                        "/ (TIMESTAMPDIFF(MONTH, MIN(subscription_date), MAX(subscription_date)) + 1 )" +
                        " AS average_salary_month" +
                        " FROM purchaselist GROUP BY course_name")) {

                    while (resultSet.next()) {
                        String courseName = resultSet.getString("course_name");
                        String averageSalaryMonth = resultSet.getString("average_salary_month");

                        System.out.println("Название курса - " + courseName + ", " +
                                "среднее количество продаж в месяц за год - " + averageSalaryMonth);
                    }
                }
            }
        }
    }
}
