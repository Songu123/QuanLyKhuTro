package project.quanlykhutro.services;

import project.ctdl.DoublyLinkedListChiTietHoaDon;
import project.quanlykhutro.dao.DAO;
import project.quanlykhutro.models.ChiTietHoaDon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiTietHoaDonService {
    public static DAO dao = new DAO();
    public static Connection conn = dao.connectionDB();

    // Thêm chi tiết hóa đơn
    public static void addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        String sql = "INSERT INTO ChiTietHoaDon (MaHoaDon, MaDichVu, SoLuong, DonGia, ThanhTien) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, chiTietHoaDon.getMaHoaDon());
            ps.setInt(2, chiTietHoaDon.getMaDichVu());
            ps.setFloat(3, chiTietHoaDon.getSoLuong());
            ps.setFloat(4, chiTietHoaDon.getDonGia());
            ps.setFloat(5, chiTietHoaDon.getThanhTien());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Lấy chi tiết hóa đơn theo mã hóa đơn
    public static ChiTietHoaDon getChiTietHoaDonById(int maHoaDon, int maDichVu) {
        ChiTietHoaDon chiTietHoaDon = null;
        String sql = "SELECT * FROM ChiTietHoaDon WHERE MaHoaDon = ? AND MaDichVu = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maHoaDon);
            ps.setInt(2, maDichVu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setMaHoaDon(rs.getInt("MaHoaDon"));
                chiTietHoaDon.setMaDichVu(rs.getInt("MaDichVu"));
                chiTietHoaDon.setSoLuong(rs.getFloat("SoLuong"));
                chiTietHoaDon.setDonGia(rs.getFloat("DonGia"));
                chiTietHoaDon.setThanhTien(rs.getFloat("ThanhTien"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return chiTietHoaDon;
    }

    // Lấy danh sách tất cả chi tiết hóa đơn
    public static void getListAllChiTietHoaDon(DoublyLinkedListChiTietHoaDon listChiTietHoaDon) {
        String sql = "SELECT * FROM ChiTietHoaDon";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setMaHoaDon(rs.getInt("MaHoaDon"));
                chiTietHoaDon.setMaDichVu(rs.getInt("MaDichVu"));
                chiTietHoaDon.setSoLuong(rs.getFloat("SoLuong"));
                chiTietHoaDon.setDonGia(rs.getFloat("DonGia"));
                chiTietHoaDon.setThanhTien(rs.getFloat("ThanhTien"));

                listChiTietHoaDon.addChiTietHoaDon(chiTietHoaDon);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Cập nhật chi tiết hóa đơn
    public static void updateChiTietHoaDon(ChiTietHoaDon chiTietHoaDon, int maHoaDon, int maDichVu) {
        String sql = "UPDATE ChiTietHoaDon SET SoLuong = ?, DonGia = ?, ThanhTien = ? WHERE MaHoaDon = ? AND MaDichVu = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFloat(1, chiTietHoaDon.getSoLuong());
            ps.setFloat(2, chiTietHoaDon.getDonGia());
            ps.setFloat(3, chiTietHoaDon.getThanhTien());
            ps.setInt(4, maHoaDon);
            ps.setInt(5, maDichVu);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Xóa chi tiết hóa đơn
    public static void deleteChiTietHoaDon(int maHoaDon, int maDichVu) {
        String sql = "DELETE FROM ChiTietHoaDon WHERE MaHoaDon = ? AND MaDichVu = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maHoaDon);
            ps.setInt(2, maDichVu);
            if (ps.executeUpdate() > 0) {
                System.out.println("Xóa chi tiết hóa đơn thành công!");
            } else {
                System.out.println("Xóa chi tiết hóa đơn không thành công!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Lấy chi tiết hóa đơn cuối cùng
    public static ChiTietHoaDon getLastRow() {
        String sql = "SELECT * FROM ChiTietHoaDon ORDER BY MaHoaDon DESC LIMIT 1";
        ChiTietHoaDon chiTietHoaDon = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setMaHoaDon(resultSet.getInt("MaHoaDon"));
                chiTietHoaDon.setMaDichVu(resultSet.getInt("MaDichVu"));
                chiTietHoaDon.setSoLuong(resultSet.getFloat("SoLuong"));
                chiTietHoaDon.setDonGia(resultSet.getFloat("DonGia"));
                chiTietHoaDon.setThanhTien(resultSet.getFloat("ThanhTien"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return chiTietHoaDon;
    }
}

