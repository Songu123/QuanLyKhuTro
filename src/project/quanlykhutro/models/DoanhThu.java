package project.quanlykhutro.models;

import java.util.ArrayList;
import java.util.List;

public class DoanhThu {
    String thang;
    double tongDoanhThu;
    int soLuongHoaDon;
    double soTienDaThanhToan;
    double soTienChuaThanhToan;

    public DoanhThu() {};

    public DoanhThu(String thang, double tongDoanhThu, int soLuongHoaDon, double soTienDaThanhToan, double soTienChuaThanhToan) {
        this.thang = thang;
        this.tongDoanhThu = tongDoanhThu;
        this.soLuongHoaDon = soLuongHoaDon;
        this.soTienDaThanhToan = soTienDaThanhToan;
        this.soTienChuaThanhToan = soTienChuaThanhToan;
    }

    public double getPhanTramDaThanhToan() {
        return (soTienDaThanhToan / tongDoanhThu) * 100;
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-19.0f | %-15d | %-26.0f | %-27.0f | %-23.1f%% |",
                thang, tongDoanhThu, soLuongHoaDon, soTienDaThanhToan, soTienChuaThanhToan, getPhanTramDaThanhToan());
    }

    public String getThang() {
        return thang;
    }

    public double getTongDoanhThu() {
        return tongDoanhThu;
    }

    public int getSoLuongHoaDon() {
        return soLuongHoaDon;
    }

    public double getSoTienDaThanhToan() {
        return soTienDaThanhToan;
    }

    public double getSoTienChuaThanhToan() {
        return soTienChuaThanhToan;
    }
}
