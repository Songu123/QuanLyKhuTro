package project.quanlykhutro.services;

import project.ctdl.DoublyLinkedListDichVu;
import project.quanlykhutro.dao.DAO;
import project.quanlykhutro.models.DichVu;
import project.quanlykhutro.models.DichVu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DichVuService {
    public static DAO dao = new DAO();
    public static Connection conn = dao.connectionDB();

    public static void addDichVu(DichVu dichVu) {
        String sql = "INSERT INTO DichVu (TenDichVu, DonGia, TrangThai) VALUES (?, ?, ?)";
        try {
            // Khởi tạo PreparedStatement
            PreparedStatement ps = conn.prepareStatement(sql);

            // Thiết lập các tham số cho câu truy vấn
            ps.setString(1, dichVu.getTenDichVu());
            ps.setFloat(2, dichVu.getDonGia());
            ps.setString(3, dichVu.getTrangThai());

            // Thực hiện cập nhật
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Đã thêm dịch vụ thành công: " + dichVu.getTenDichVu());
            } else {
                System.out.println("Không có dịch vụ nào được thêm.");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm dịch vụ: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }



    public static DichVu getDichVuById(int maDichVu) {
        DichVu dichVu = null;
        String sql = "SELECT * FROM DichVu WHERE MaDichVu = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maDichVu);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dichVu = new DichVu();
                dichVu.setMaDichVu(rs.getInt("MaDichVu"));
                dichVu.setTenDichVu(rs.getString("TenDichVu"));
                dichVu.setDonGia(rs.getFloat("DonGia"));
                dichVu.setTrangThai(rs.getString("TrangThai"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dichVu;
    }

    public static void getListAllDichVu(DoublyLinkedListDichVu listDichVu) {
        String sql = "SELECT * FROM DichVu";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DichVu dichVu = new DichVu();
                dichVu.setMaDichVu(rs.getInt("MaDichVu"));
                dichVu.setTenDichVu(rs.getString("TenDichVu"));
                dichVu.setDonGia(rs.getFloat("DonGia"));
                dichVu.setTrangThai(rs.getString("TrangThai"));

                listDichVu.addLast(dichVu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void updateDichVu(DichVu dichVu, int maDichVu) {
        String sql = "UPDATE DichVu SET TenDichVu = ?, DonGia = ?  WHERE MaDichVu = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dichVu.getTenDichVu());
            ps.setFloat(2, dichVu.getDonGia());
            ps.setInt(3, maDichVu);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateStatus(int maDichVu, String trangThai) {
        String sql = "UPDATE DichVu SET TrangThai = ?  WHERE MaDichVu = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, trangThai);
            ps.setInt(2, maDichVu);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteDichVu(int maDichVu) {
        String sql = "DELETE FROM DichVu WHERE MaDichVu = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maDichVu);

            if (ps.executeUpdate() > 0) {
                System.out.println("Xoá dịch vụ thành công!");
            } else {
                System.out.println("Xoá dịch vụ không thành công!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static DichVu getLastRow() {
        String sql = "SELECT * FROM DichVu ORDER BY MaDichVu DESC LIMIT 1;";
        DichVu dichVu = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                dichVu = new DichVu();
                dichVu.setMaDichVu(resultSet.getInt("MaDichVu"));
                dichVu.setTenDichVu(resultSet.getString("TenDichVu"));
                dichVu.setDonGia(resultSet.getFloat("DonGia"));
                dichVu.setTrangThai(resultSet.getString("TrangThai"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dichVu;
    }


}
