package project.quanlykhutro.models;

import java.time.LocalDate;

public class HopDong {
    private int maHopDong;
    private int maPhong;
    private int maNguoiThue;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private float giaThueHopDong;
    private float tienCoc;
    private String trangThai;

    public HopDong( int maPhong, int maNguoiThue, LocalDate ngayBatDau, LocalDate ngayKetThuc, float giaThueHopDong, float tienCoc, String trangThai) {
        this.maHopDong = maHopDong;
        this.maPhong = maPhong;
        this.maNguoiThue = maNguoiThue;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giaThueHopDong = giaThueHopDong;
        this.tienCoc = tienCoc;
        this.trangThai = trangThai;
    }

    public HopDong(int maPhong, int maNguoiThue, LocalDate ngayBatDau, LocalDate ngayKetThuc, float giaThueHopDong, float tienCoc) {
        this.maPhong = maPhong;
        this.maNguoiThue = maNguoiThue;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giaThueHopDong = giaThueHopDong;
        this.tienCoc = tienCoc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public HopDong() {
    }

    public int getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(int maHopDong) {
        this.maHopDong = maHopDong;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public int getMaNguoiThue() {
        return maNguoiThue;
    }

    public void setMaNguoiThue(int maNguoiThue) {
        this.maNguoiThue = maNguoiThue;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public float getGiaThueHopDong() {
        return giaThueHopDong;
    }

    public void setGiaThueHopDong(float giaThueHopDong) {
        this.giaThueHopDong = giaThueHopDong;
    }

    public float getTienCoc() {
        return tienCoc;
    }

    public void setTienCoc(float tienCoc) {
        this.tienCoc = tienCoc;
    }

    public void giaHan(int thangGiaHan) {
        LocalDate newEndDate = this.ngayKetThuc.plusMonths(thangGiaHan);
        this.ngayKetThuc = newEndDate;
    }

}
