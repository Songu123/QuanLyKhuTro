package project.quanlykhutro.services;

import project.quanlykhutro.dao.DAO;
import project.quanlykhutro.models.NguoiThue;
import project.quanlykhutro.models.Phong;

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
    public DAO dao = new DAO();
    public Connection conn = dao.connectionDB();
    public DateFormat format  = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public void addNguoiThue(NguoiThue nguoiThue){
        String sql = "INSERT INTO NguoiThue VALUES (?, ?, ?, ?, ?)";
//        Date date = format.parse(nguoiThue.getNgaySinh());
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nguoiThue.getTen());
            ps.setDate(2, java.sql.Date.valueOf(nguoiThue.getNgaySinh()) );
            ps.setByte(3, nguoiThue.getGioiTinh());
            ps.setString(4, nguoiThue.getDiaChi());
            ps.setString(5, nguoiThue.getSoDienThoai());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public NguoiThue getNguoiThueById(int maNguoiThue) {
        NguoiThue nguoiThue = null;
        String sql = "SELECT * FROM NguoiThue WHERE MaNguoiThue = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maNguoiThue);
            ps.executeUpdate();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nguoiThue = new NguoiThue();
                nguoiThue.setMaNguoiThue(rs.getInt("MaNguoiThue"));
                nguoiThue.setTen(rs.getString("Ten"));
                nguoiThue.setNgaySinh(rs.getDate("NgaySinh").toLocalDate());
                nguoiThue.setGioiTinh(rs.getByte("GioiTinh"));
                nguoiThue.setDiaChi(rs.getString("DiaChi"));
                nguoiThue.setSoDienThoai(rs.getString("SoDienThoai"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nguoiThue;
    }

    public List<NguoiThue> getListAllNguoiThue() {
        String sql = "SELECT * FROM NguoiThue";
        List<NguoiThue> listNguoiThue = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NguoiThue nguoiThue = new NguoiThue();
                nguoiThue.setMaNguoiThue(rs.getInt("MaNguoiThue"));
                nguoiThue.setTen(rs.getString("Ten"));
                nguoiThue.setNgaySinh(rs.getDate("NgaySinh").toLocalDate());
                nguoiThue.setGioiTinh(rs.getByte("GioiTinh"));
                nguoiThue.setDiaChi(rs.getString("DiaChi"));
                nguoiThue.setSoDienThoai(rs.getString("SoDienThoai"));

                listNguoiThue.add(nguoiThue);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listNguoiThue;
    }

    public void updateNguoiThue(NguoiThue nguoiThue, int maNguoiThue) {
        String sql = "UPDATE NguoiThue SET MaNguoiThue = ?, Ten = ?, NgaySinh = ?, GioiTinh = ?, DiaChi = ?, SoDienThoai = ?  WHERE MaNguoiThue = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nguoiThue.getTen());
            ps.setDate(2,  java.sql.Date.valueOf(nguoiThue.getNgaySinh()));
            ps.setByte(3, nguoiThue.getGioiTinh());
            ps.setString(4, nguoiThue.getDiaChi());
            ps.setString(5, nguoiThue.getSoDienThoai());
            ps.setInt(6, maNguoiThue);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteNguoiThue(int maNguoiThue) {
        String sql = "DELETE FROM NguoiThue WHERE MaNguoiThue = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maNguoiThue);

            if (ps.executeUpdate() > 0) {
                System.out.println("Xoá người thuê thành công!");
            }else  {
                System.out.println("Xoá người thuê không thành công!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
