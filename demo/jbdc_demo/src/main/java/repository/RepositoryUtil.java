package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RepositoryUtil {
    public static Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/laptop_manager";
        String user = "root";
        String password = "12345678";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected!");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void closeConnection(Connection conn) {
        try {
            if(conn!=null)
                conn.close();
            System.out.println("Disconnected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        closeConnection(conn);

    }
}
