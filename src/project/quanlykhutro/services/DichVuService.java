package project.quanlykhutro.services;

import project.quanlykhutro.dao.DAO;
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

    public void addDichVu(DichVu dichVu) {
        String sql = "INSERT INTO DichVu VALUES (?, ?, ?)";
//        Date date = format.parse(nguoiThue.getNgaySinh());
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, dichVu.getMaDichVu());
            ps.setString(2, dichVu.getTenDichVu());
            ps.setFloat(3, dichVu.getDonGia());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DichVu getDichVuById(int maDichVu) {
        DichVu dichVu = null;
        String sql = "SELECT * FROM DichVu WHERE MaDichVu = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maDichVu);
            ps.executeUpdate();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dichVu = new DichVu();
                dichVu.setMaDichVu(rs.getInt("MaDichVu"));
                dichVu.setTenDichVu(rs.getString("TenDichVu"));
                dichVu.setDonGia(rs.getFloat("DonGia"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dichVu;
    }

    public List<DichVu> getListAllDichVu() {
        String sql = "SELECT * FROM DichVu";
        List<DichVu> listDichVu = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DichVu dichVu = new DichVu();
                dichVu.setMaDichVu(rs.getInt("MaDichVu"));
                dichVu.setTenDichVu(rs.getString("TenDichVu"));
                dichVu.setDonGia(rs.getFloat("DonGia"));

                listDichVu.add(dichVu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listDichVu;
    }

    public void updateDichVu(DichVu dichVu, int maDichVu) {
        String sql = "UPDATE DichVu SET MaDichVu = ?, TenDichVu = ?, DonGia = ?  WHERE MaDichVu = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, dichVu.getMaDichVu());
            ps.setString(2, dichVu.getTenDichVu());
            ps.setFloat(3, dichVu.getDonGia());
            ps.setInt(4, maDichVu);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteDichVu(int maDichVu) {
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
}
