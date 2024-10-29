package project.quanlykhutro.services;

import project.ctdl.DoublyLinkedListHopDong;
import project.quanlykhutro.dao.DAO;
import project.quanlykhutro.models.HopDong;
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

public class HopDongService {
    public static DAO dao = new DAO();
    public static Connection conn = dao.connectionDB();
    public DateFormat format  = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public static void addHopDong(HopDong hopDong){
        String sql = "INSERT INTO HopDong (MaPhong, MaNguoiThue, NgayBatDau, NgayKetThuc, GiaThueHopDong, TienCoc) VALUES (?, ?, ?, ?, ?, ?)\n";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, hopDong.getMaPhong());
            ps.setInt(2, hopDong.getMaNguoiThue());
            ps.setDate(3, java.sql.Date.valueOf(hopDong.getNgayBatDau()));
            ps.setDate(4, java.sql.Date.valueOf(hopDong.getNgayKetThuc()));
            ps.setFloat(5, hopDong.getGiaThueHopDong());
            ps.setFloat(6, hopDong.getTienCoc());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static HopDong getHopDongById(int maHopDong) {
        HopDong hopDong = null;
        String sql = "SELECT * FROM HopDong WHERE MaHopDong = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maHopDong);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                hopDong = new HopDong();
                hopDong.setMaHopDong(rs.getInt("MaHopDong"));
                hopDong.setMaPhong(rs.getInt("MaPhong"));
                hopDong.setMaNguoiThue(rs.getInt("MaNguoiThue"));
                hopDong.setNgayBatDau(rs.getDate("NgayBatDau").toLocalDate());
                hopDong.setNgayKetThuc(rs.getDate("NgayKetThuc").toLocalDate());
                hopDong.setGiaThueHopDong(rs.getFloat("GiaThueHopDong"));
                hopDong.setTienCoc(rs.getFloat("TienCoc"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hopDong;
    }

    public static void getListAllHopDong(DoublyLinkedListHopDong listHopDong) {
        String sql = "SELECT * FROM HopDong";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HopDong hopDong = new HopDong();
                hopDong.setMaHopDong(rs.getInt("MaHopDong"));
                hopDong.setMaPhong(rs.getInt("MaPhong"));
                hopDong.setMaNguoiThue(rs.getInt("MaNguoiThue"));
                hopDong.setNgayBatDau(rs.getDate("NgayBatDau").toLocalDate());
                hopDong.setNgayKetThuc(rs.getDate("NgayKetThuc").toLocalDate());
                hopDong.setGiaThueHopDong(rs.getFloat("GiaThueHopDong"));
                hopDong.setTienCoc(rs.getFloat("TienCoc"));
                listHopDong.addLast(hopDong);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateHopDong(HopDong hopDong, int maHopDong) {
        String sql = "UPDATE HopDong SET MaHopDong = ?, MaNguoiThue = ?, NgayBatDau = ?, NgayKetThuc = ?, GiaThueHopDong = ?, TienCoc = ?  WHERE MaHopDong = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, hopDong.getMaPhong());
            ps.setInt(2, hopDong.getMaNguoiThue());
            ps.setDate(3, java.sql.Date.valueOf(hopDong.getNgayBatDau()));
            ps.setDate(4, java.sql.Date.valueOf(hopDong.getNgayKetThuc()));
            ps.setFloat(5, hopDong.getGiaThueHopDong());
            ps.setFloat(6, hopDong.getTienCoc());
            ps.setInt(7, maHopDong);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteHopDong(int maHopDong) {
        String sql = "DELETE FROM HopDong WHERE MaHopDong = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maHopDong);

            if (ps.executeUpdate() > 0) {
                System.out.println("Xoá hợp đồng thành công!");
            }else  {
                System.out.println("Xoá hợp đồng không thành công!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static HopDong getLastRow() {
        String sql = "SELECT * FROM HopDong ORDER BY MaHopDong DESC LIMIT 1;";
        HopDong hopDong = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                hopDong = new HopDong();
                hopDong.setMaHopDong(resultSet.getInt("MaHopDong"));
                hopDong.setMaNguoiThue(resultSet.getInt("MaNguoiThue"));
                hopDong.setMaPhong(resultSet.getInt("MaPhong"));
                hopDong.setNgayBatDau(resultSet.getDate("NgayBatDau").toLocalDate());
                hopDong.setNgayKetThuc(resultSet.getDate("NgayKetThuc").toLocalDate());
                hopDong.setGiaThueHopDong(resultSet.getFloat("GiaThueHopDong"));
                hopDong.setTienCoc(resultSet.getFloat("TienCoc"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý ngoại lệ
        }
        return hopDong;
    }

    public static boolean checkMaNguoiThue(int maNguoiThue) {
        String sql = "SELECT COUNT(*) FROM NguoiThue WHERE MaNguoiThue = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maNguoiThue);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void giaHanHopDong(HopDong hopDong) {
        String sql = "UPDATE HopDong SET NgayKetThuc = ? WHERE MaHopDong = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(hopDong.getNgayKetThuc()));
            ps.setInt(2, hopDong.getMaHopDong());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
