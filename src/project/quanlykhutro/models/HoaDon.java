package project.quanlykhutro.models;

import java.time.LocalDate;

public class HoaDon {
    private int maHoaDon;
    private int maHopDong;
    private LocalDate ngayPhatHanh;
    private LocalDate ngayDenHan;
    private float tongTien;
    private String trangThai;

    public HoaDon() {
    }

    public HoaDon(int maHoaDon, int maHopDong, LocalDate ngayPhatHanh, LocalDate ngayDenHan, float tongTien, String trangThai) {
        this.maHoaDon = maHoaDon;
        this.maHopDong = maHopDong;
        this.ngayPhatHanh = ngayPhatHanh;
        this.ngayDenHan = ngayDenHan;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(int maHopDong) {
        this.maHopDong = maHopDong;
    }

    public LocalDate getNgayPhatHanh() {
        return ngayPhatHanh;
    }

    public void setNgayPhatHanh(LocalDate ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public LocalDate getNgayDenHan() {
        return ngayDenHan;
    }

    public void setNgayDenHan(LocalDate ngayDenHan) {
        this.ngayDenHan = ngayDenHan;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
