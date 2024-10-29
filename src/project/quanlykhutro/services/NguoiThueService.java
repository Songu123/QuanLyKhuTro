package project.quanlykhutro.services;

import project.ctdl.DoublyLinkedListNguoiThue;
import project.quanlykhutro.dao.DAO;
import project.quanlykhutro.models.NguoiThue;
import project.quanlykhutro.models.NguoiThue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NguoiThueService {
    public static DAO dao = new DAO();
    public static Connection conn = dao.connectionDB();
    public DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public static void addNguoiThue(NguoiThue nguoiThue) {
        // Nếu bảng có cột tự động tăng, bạn cần chỉ định cụ thể các cột trong câu lệnh SQL
        String sql = "INSERT INTO NguoiThue (Ten, NgaySinh, GioiTinh, DiaChi, SoDienThoai, TrangThai) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            // Thiết lập các giá trị cho các cột
            ps.setString(1, nguoiThue.getTen());

            // Chuyển đổi LocalDate thành java.sql.Date
            ps.setDate(2, java.sql.Date.valueOf(nguoiThue.getNgaySinh()));

            ps.setString(3, nguoiThue.getGioiTinh());
            ps.setString(4, nguoiThue.getDiaChi());
            ps.setString(5, nguoiThue.getSoDienThoai());
            ps.setString(6, nguoiThue.getTrangThai());

            // Thực thi lệnh SQL
            ps.executeUpdate();

        } catch (SQLException e) {
            // Xử lý lỗi SQL
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static NguoiThue getNguoiThueById(int maNguoiThue) {
        NguoiThue nguoiThue = null;
        String sql = "SELECT * FROM NguoiThue WHERE MaNguoiThue = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maNguoiThue);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nguoiThue = new NguoiThue();
                nguoiThue.setMaNguoiThue(rs.getInt("MaNguoiThue"));
                nguoiThue.setTen(rs.getString("Ten"));
                nguoiThue.setNgaySinh(rs.getDate("NgaySinh").toLocalDate());
                nguoiThue.setGioiTinh(rs.getString("GioiTinh"));
                nguoiThue.setDiaChi(rs.getString("DiaChi"));
                nguoiThue.setSoDienThoai(rs.getString("SoDienThoai"));
                nguoiThue.setTrangThai(rs.getString("TrangThai"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nguoiThue;
    }

    public static void getListAllNguoiThue(DoublyLinkedListNguoiThue listNguoiThue) {
        String sql = "SELECT * FROM NguoiThue";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NguoiThue nguoiThue = new NguoiThue();
                nguoiThue.setMaNguoiThue(rs.getInt("MaNguoiThue"));
                nguoiThue.setTen(rs.getString("Ten"));
                nguoiThue.setNgaySinh(rs.getDate("NgaySinh").toLocalDate());
                nguoiThue.setGioiTinh(rs.getString("GioiTinh"));
                nguoiThue.setDiaChi(rs.getString("DiaChi"));
                nguoiThue.setSoDienThoai(rs.getString("SoDienThoai"));
                nguoiThue.setTrangThai(rs.getString("TrangThai"));

                listNguoiThue.addLast(nguoiThue);
                System.out.println("Thêm thành công!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateNguoiThue(NguoiThue nguoiThue, int maNguoiThue) {
        String sql = "UPDATE NguoiThue SET Ten = ?, NgaySinh = ?, GioiTinh = ?, DiaChi = ?, SoDienThoai = ?,TrangThai = ?  WHERE MaNguoiThue = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nguoiThue.getTen());
            ps.setDate(2, java.sql.Date.valueOf(nguoiThue.getNgaySinh()));
            ps.setString(3, nguoiThue.getGioiTinh());
            ps.setString(4, nguoiThue.getDiaChi());
            ps.setString(5, nguoiThue.getSoDienThoai());
            ps.setString(6, nguoiThue.getTrangThai());
            ps.setInt(7, maNguoiThue);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateStatusNguoiThue(int maNguoiThue, String trangThai) {
        String sql = "UPDATE NguoiThue SET TrangThai = ?  WHERE MaNguoiThue = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, trangThai);
            ps.setInt(2, maNguoiThue);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteNguoiThue(int maNguoiThue) {
        String sql = "DELETE FROM NguoiThue WHERE MaNguoiThue = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maNguoiThue);

            if (ps.executeUpdate() > 0) {
                System.out.println("Xoá người thuê thành công!");
            } else {
                System.out.println("Xoá người thuê không thành công!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static NguoiThue getLastRow() {
        String sql = "SELECT * FROM NguoiThue ORDER BY MaNguoiThue DESC LIMIT 1;";
        NguoiThue nguoiThue = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                nguoiThue = new NguoiThue();
                nguoiThue.setMaNguoiThue(resultSet.getInt("MaNguoiThue"));
                nguoiThue.setTen(resultSet.getString("Ten"));
                nguoiThue.setNgaySinh(resultSet.getDate("NgaySinh").toLocalDate());
                nguoiThue.setGioiTinh(resultSet.getString("GioiTinh"));
                nguoiThue.setDiaChi(resultSet.getString("DiaChi"));
                nguoiThue.setSoDienThoai(resultSet.getString("SoDienThoai"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nguoiThue;
    }
}
