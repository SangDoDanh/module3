package model.repository.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    public static Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/furuma_jsp_servlet";
        String user = "root";
        String password = "12345678";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            return  conn;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Connection conn) {
        try {
            if(conn != null)
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
