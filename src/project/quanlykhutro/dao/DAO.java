package project.quanlykhutro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    public static Connection conn;
    private static String DB_URL = "jdbc:mysql://localhost:3306/quanlykhutro";
    private static String USER_NAME = "vanson";
    private static String PASSWORD = "vanson@1234";

    public Connection connectionDB() {
        try {
            // Using the updated MySQL driver
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("Kết nối MySQL thành công!");
        } catch (SQLException ex) {
            System.out.println("Kêt nối MySQL lỗi");
            ex.printStackTrace();
        }
        return conn;
    }
}
