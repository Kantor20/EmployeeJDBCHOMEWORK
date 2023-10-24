package pl.kurs.Zadanie01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/factory_employment?useSSL=false&serverTimezone=UTC",
                "root",
                "root");
    }
}
