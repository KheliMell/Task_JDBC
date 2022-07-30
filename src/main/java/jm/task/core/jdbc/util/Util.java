package jm.task.core.jdbc.util;

import jm.task.core.jdbc.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = Main.getURL();
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private Connection connection;

    public Util() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }
}
