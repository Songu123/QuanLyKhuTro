package project.quanlykhutro.services;

import project.quanlykhutro.dao.DAO;
import project.quanlykhutro.models.QuanLy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuanLyService {

    public static DAO data = new DAO();
    public static Connection conn = data.connectionDB();

    //    Chèn dữ liệu phòng
    public void addQuanLy(QuanLy quanLy) {
        String sql = "INSERT INTO QuanLy VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, quanLy.getHoTen());
            ps.setString(2, quanLy.getSoDienThoai());
            ps.setString(3, quanLy.getEmail());
            ps.setString(4, quanLy.getDiaChi());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public QuanLy getQuanLyById(int maQuanLy) {
        QuanLy quanLy = null;
        String sql = "SELECT * FROM QuanLy WHERE MaQuanLy = ?";

        try (// Kết nối tới cơ sở dữ liệu
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maQuanLy);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                quanLy = new QuanLy();
                quanLy.setMaQuanLy(resultSet.getInt("MaQuanLy"));
                quanLy.setHoTen(resultSet.getString("DienTich"));
                quanLy.setSoDienThoai(resultSet.getString("GiaThue"));
                quanLy.setEmail(resultSet.getString("TrangThai"));
                quanLy.setDiaChi(resultSet.getString("MoTa"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý ngoại lệ
        }
        return quanLy;
    }

//    public static void getAllQuanLy(DoublyLinkedListQuanLy listQuanLy) {
//        String sql = "SELECT * FROM QuanLy";
//        try(PreparedStatement ps = conn.prepareStatement(sql)) {
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                QuanLy quanLy = new QuanLy();
//                quanLy.setMaQuanLy(rs.getInt("MaQuanLy"));
//                quanLy.setDienTich(rs.getFloat("DienTich"));
//                quanLy.setGiaThue(rs.getFloat("GiaThue"));
//                quanLy.setTrangThai(rs.getString("TrangThai"));
//                quanLy.setMoTa(rs.getString("MoTa"));
//                quanLy.setMaQuanLy(rs.getInt("MaQuanLy"));
//
//                listQuanLy.addLast(quanLy);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public void updateQuanLy(QuanLy quanLy, int maQuanLy) {
//        String sql = "UPDATE QuanLy SET DienTich = ?, GiaThue = ?, TrangThai = ?, MoTa = ?, MaQuanLy = ? WHERE maQuanLy = ?";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setFloat(1, quanLy.getDienTich());
//            ps.setFloat(2, quanLy.getGiaThue());
//            ps.setString(3, quanLy.getTrangThai());
//            ps.setString(4, quanLy.getMoTa());
//            ps.setInt(5, quanLy.getMaQuanLy());
//            ps.setInt(6, maQuanLy);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void deleteQuanLy(int maQuanLy) {
        String sql = "DELETE FROM QuanLy WHERE MaQuanLy = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maQuanLy);

            if (ps.executeUpdate() > 0) {
                System.out.println("Xoá phòng thành công!");
            }else  {
                System.out.println("Xoá phòng không thành công!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
