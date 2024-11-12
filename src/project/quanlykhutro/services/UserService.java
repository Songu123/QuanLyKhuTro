package project.quanlykhutro.services;

import project.quanlykhutro.dao.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {

    public static DAO data = new DAO();
    public static Connection conn = data.connectionDB();

    public static boolean checkLogin(String username, String password) {
        String sql = "SELECT * FROM User WHERE TenDangNhap = ? AND MatKhau = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
