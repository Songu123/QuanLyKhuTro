package project.quanlykhutro.services;

import project.ctdl.DoublyLinkedListPhong;
import project.quanlykhutro.dao.DAO;
import project.quanlykhutro.models.Phong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhongService {
    public static DAO data = new DAO();
    public static Connection conn = data.connectionDB();


    //    Chèn dữ liệu phòng
    public static void addPhong(Phong phong) {
        String sql = "INSERT INTO Phong (DienTich, GiaThue, TrangThai, MoTa, MaQuanLy) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            // Set the values for each placeholder in the SQL query
            ps.setFloat(1, phong.getDienTich());
            ps.setFloat(2, phong.getGiaThue());
            ps.setString(3, phong.getTrangThai());
            ps.setString(4, phong.getMoTa());
            ps.setInt(5, phong.getMaQuanLy());

            // Use executeUpdate for INSERT/UPDATE/DELETE operations
            ps.executeUpdate();

        } catch (SQLException e) {
            // Print the stack trace for debugging
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public static Phong getPhongById(int maPhong) {
        Phong phong = null;
        String sql = "SELECT * FROM Phong WHERE MaPhong = ?";

        try (// Kết nối tới cơ sở dữ liệu
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maPhong);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                phong = new Phong();
                phong.setMaPhong(resultSet.getInt("MaPhong"));
                phong.setDienTich(resultSet.getFloat("DienTich"));
                phong.setGiaThue(resultSet.getFloat("GiaThue"));
                phong.setTrangThai(resultSet.getString("TrangThai"));
                phong.setMoTa(resultSet.getString("MoTa"));
                phong.setMaQuanLy(resultSet.getInt("MaQuanLy"));

            }
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý ngoại lệ
        }
        return phong;
    }

    public static void getAllPhong(DoublyLinkedListPhong listPhong) {
        String sql = "SELECT * FROM Phong";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Phong phong = new Phong();
                phong.setMaPhong(rs.getInt("MaPhong"));
                phong.setDienTich(rs.getFloat("DienTich"));
                phong.setGiaThue(rs.getFloat("GiaThue"));
                phong.setTrangThai(rs.getString("TrangThai"));
                phong.setMoTa(rs.getString("MoTa"));
                phong.setMaQuanLy(rs.getInt("MaQuanLy"));

                listPhong.addLast(phong);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updatePhong(Phong phong, int maPhong) {
        String sql = "UPDATE Phong SET DienTich = ?, GiaThue = ?, TrangThai = ?, MoTa = ?, MaQuanLy = ? WHERE maPhong = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFloat(1, phong.getDienTich());
            ps.setFloat(2, phong.getGiaThue());
            ps.setString(3, phong.getTrangThai());
            ps.setString(4, phong.getMoTa());
            ps.setInt(5, phong.getMaQuanLy());
            ps.setInt(6, maPhong);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deletePhong(int maPhong) {
        String sql = "DELETE FROM Phong WHERE MaPhong = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maPhong);

            if (ps.executeUpdate() > 0) {
                System.out.println("Xoá phòng thành công!");
            } else {
                System.out.println("Xoá phòng không thành công!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Phong getLastRow() {
        String sql = "SELECT * FROM Phong ORDER BY MaPhong DESC LIMIT 1;";
        Phong phong = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                phong = new Phong();
                phong.setMaPhong(resultSet.getInt("MaPhong"));
                phong.setDienTich(resultSet.getFloat("DienTich"));
                phong.setGiaThue(resultSet.getFloat("GiaThue"));
                phong.setTrangThai(resultSet.getString("TrangThai"));
                phong.setMoTa(resultSet.getString("MoTa"));
                phong.setMaQuanLy(resultSet.getInt("MaQuanLy"));

            }
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý ngoại lệ
        }
        return phong;
    }
}
