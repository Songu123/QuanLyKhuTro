package project.quanlykhutro.services;

import project.quanlykhutro.dao.DAO;
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
    public DAO dao = new DAO();
    public Connection conn = dao.connectionDB();
    public DateFormat format  = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public void addHopDong(HopDong hopDong){
        String sql = "INSERT INTO HopDong VALUES (?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, hopDong.getMaHopDong());
            ps.setInt(2, hopDong.getMaPhong());
            ps.setInt(3, hopDong.getMaNguoiThue());
            ps.setDate(4, java.sql.Date.valueOf(hopDong.getNgayBatDau()));
            ps.setDate(5, java.sql.Date.valueOf(hopDong.getNgayKetThuc()));
            ps.setFloat(6, hopDong.getGiaThueHopDong());
            ps.setFloat(7, hopDong.getTienCoc());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public HopDong getHopDongById(int maHopDong) {
        HopDong hopDong = null;
        String sql = "SELECT * FROM HopDong WHERE MaHopDong = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maHopDong);
            ps.executeUpdate();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                hopDong = new HopDong();
                hopDong.setMaHopDong(rs.getInt("MaHopDong"));
                hopDong.setMaPhong(rs.getInt("MaPhong"));
                hopDong.setMaNguoiThue(rs.getInt("MaNguoiThue"));
                hopDong.setNgayBatDau(rs.getDate("NgayBatDau").toLocalDate());
                hopDong.setNgayKetThuc(rs.getDate("NgayKetThuc").toLocalDate());
                hopDong.setGiaThueHopDong(rs.getFloat("SoDienThoai"));
                hopDong.setTienCoc(rs.getFloat("SoDienThoai"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hopDong;
    }

    public List<HopDong> getListAllHopDong() {
        String sql = "SELECT * FROM HopDong";
        List<HopDong> listHopDong = new ArrayList<>();
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
                hopDong.setGiaThueHopDong(rs.getFloat("SoDienThoai"));
                hopDong.setTienCoc(rs.getFloat("SoDienThoai"));
                listHopDong.add(hopDong);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listHopDong;
    }

    public void updateHopDong(HopDong hopDong, int maHopDong) {
        String sql = "UPDATE HopDong SET MaHopDong = ?, MaPhong = ?, MaNguoiThue = ?, NgayBatDau = ?, NgayKetThuc = ?, GiaThueHopDong = ?, TienCoc = ?  WHERE MaHopDong = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, hopDong.getMaHopDong());
            ps.setInt(2, hopDong.getMaPhong());
            ps.setInt(3, hopDong.getMaNguoiThue());
            ps.setDate(4, java.sql.Date.valueOf(hopDong.getNgayBatDau()));
            ps.setDate(5, java.sql.Date.valueOf(hopDong.getNgayKetThuc()));
            ps.setFloat(6, hopDong.getGiaThueHopDong());
            ps.setFloat(7, hopDong.getTienCoc());
            ps.setInt(8, maHopDong);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteHopDong(int maHopDong) {
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
}
