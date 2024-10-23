package project.quanlykhutro.services;

import project.ctdl.DoublyLinkedListHoaDon;
import project.quanlykhutro.dao.DAO;
import project.quanlykhutro.models.HoaDon;
import project.quanlykhutro.models.HopDong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HoaDonService {
    public static DAO dao = new DAO();
    public static Connection conn = dao.connectionDB();
    public DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public static void addHoaDon(HoaDon hoaDon) {
        String sql = "INSERT INTO HoaDon VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, hoaDon.getMaHoaDon());
            ps.setInt(2, hoaDon.getMaHopDong());
            ps.setDate(3, java.sql.Date.valueOf(hoaDon.getNgayPhatHanh()));
            ps.setDate(4, java.sql.Date.valueOf(hoaDon.getNgayDenHan()));
            ps.setFloat(5, hoaDon.getTongTien());
            ps.setString(6, hoaDon.getTrangThai());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static HoaDon getLastRow() {
        String sql = "SELECT * FROM HoaDon ORDER BY MaHoaDon DESC LIMIT 1;";
        HoaDon hoaDon = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(resultSet.getInt("MaHoaDon"));
                hoaDon.setMaHopDong(resultSet.getInt("MaHopDong"));
                hoaDon.setNgayPhatHanh(resultSet.getDate("NgayPhatHanh").toLocalDate());
                hoaDon.setNgayDenHan(resultSet.getDate("NgayDenHan").toLocalDate());
                hoaDon.setTongTien(resultSet.getFloat("TongTien"));
                hoaDon.setTrangThai(resultSet.getString("TrangThai"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý ngoại lệ
        }
        return hoaDon;
    }

    public HoaDon getHoaDonById(int maHoaDon) {
        HoaDon hoaDon = null;
        String sql = "SELECT * FROM HoaDon WHERE MaHoaDon = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maHoaDon);
            ps.executeUpdate();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getInt("MaHoaDon"));
                hoaDon.setMaHopDong(rs.getInt("MaHopDong"));
                hoaDon.setNgayPhatHanh(rs.getDate("NgayPhatHanh").toLocalDate());
                hoaDon.setNgayDenHan(rs.getDate("NgayBatDau").toLocalDate());
                hoaDon.setTongTien(rs.getFloat("TongTien"));
                hoaDon.setTrangThai(rs.getString("TrangThai"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hoaDon;
    }

    public static void getListAllHoaDon(DoublyLinkedListHoaDon listHoaDon) {
        String sql = "SELECT * FROM HoaDon";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getInt("MaHoaDon"));
                hoaDon.setMaHopDong(rs.getInt("MaHopDong"));
                hoaDon.setNgayPhatHanh(rs.getDate("NgayPhatHanh").toLocalDate());
                hoaDon.setNgayDenHan(rs.getDate("NgayBatDau").toLocalDate());
                hoaDon.setTongTien(rs.getFloat("TongTien"));
                hoaDon.setTrangThai(rs.getString("TrangThai"));
                listHoaDon.addHoaDon(hoaDon);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateHoaDon(HoaDon hoaDon, int maHoaDon) {
        String sql = "UPDATE HoaDon SET MaHoaDon = ?, MaPhong = ?, MaNguoiThue = ?, NgayBatDau = ?, NgayKetThuc = ?, GiaThueHoaDon = ?, TienCoc = ?  WHERE MaHoaDon = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, hoaDon.getMaHoaDon());
            ps.setInt(2, hoaDon.getMaHopDong());
            ps.setDate(3, java.sql.Date.valueOf(hoaDon.getNgayPhatHanh()));
            ps.setDate(4, java.sql.Date.valueOf(hoaDon.getNgayDenHan()));
            ps.setFloat(5, hoaDon.getTongTien());
            ps.setString(6, hoaDon.getTrangThai());
            ps.executeUpdate();
            ps.setInt(8, maHoaDon);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateTrangThaiHoaDon(String trangThai, int maHoaDon) {
        String sql = "UPDATE HoaDon SET TrangThai = ?  WHERE MaHoaDon = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, trangThai);
            ps.setInt(2, maHoaDon);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteHoaDon(int maHoaDon) {
        String sql = "DELETE FROM HoaDon WHERE MaHoaDon = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maHoaDon);

            if (ps.executeUpdate() > 0) {
                System.out.println("Xoá hợp đồng thành công!");
            } else {
                System.out.println("Xoá hợp đồng không thành công!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkMaHopDong(int maHopDong) {
        String sql = "SELECT COUNT(*) FROM HopDong WHERE MaHopDong = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maHopDong);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

