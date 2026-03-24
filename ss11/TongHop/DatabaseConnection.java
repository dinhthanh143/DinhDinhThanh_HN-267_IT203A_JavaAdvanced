package ss11.TongHop;

import java.sql.Connection;
import java.sql.DriverManager;
import config.DatabaseConfig;

public class DatabaseConnection {
    private static final String DB_NAME = "MedicalAppointmentDB";

    public static Connection getConnection() throws Exception {
        Class.forName(DatabaseConfig.DRIVER);
        return DriverManager.getConnection(DatabaseConfig.getURL(DB_NAME), DatabaseConfig.USER, DatabaseConfig.PASS);
    }
}