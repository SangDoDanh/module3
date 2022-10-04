package database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class JDBCUtil {
    public static Connection getConnection() {

        Connection c = null;

        DriverManager.registerDriver();
        return c;
    }

    public static Connection closeConnection(Connection c) {

    }
}
