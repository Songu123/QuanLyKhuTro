package project.quanlykhutro.models;

import java.time.LocalDate;

public class NguoiThue {
    private int maNguoiThue;
    private String ten;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String diaChi;
    private String soDienThoai;
    private String trangThai;

    // Constructor không tham số
    public NguoiThue() {
//        this.trangThai = "Đang Chờ"; // giá trị mặc định
    }

    // Constructor có đầy đủ tham số bao gồm mã người thuê
    public NguoiThue(int maNguoiThue, String ten, LocalDate ngaySinh, String gioiTinh, String diaChi, String soDienThoai, String trangThai) {
        this.maNguoiThue = maNguoiThue;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.trangThai = trangThai;
    }

    // Constructor không bao gồm mã người thuê
    public NguoiThue(String ten, LocalDate ngaySinh, String gioiTinh, String diaChi, String soDienThoai, String trangThai) {
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.trangThai = trangThai;
    }

    // Constructor mặc định trạng thái "Đang Chờ"
    public NguoiThue(String ten, LocalDate ngaySinh, String gioiTinh, String diaChi, String soDienThoai) {
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.trangThai = "Đang Chờ"; // giá trị mặc định
    }

    // Các getter và setter
    public int getMaNguoiThue() {
        return maNguoiThue;
    }

    public void setMaNguoiThue(int maNguoiThue) {
        this.maNguoiThue = maNguoiThue;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "NguoiThue{" +
                "maNguoiThue=" + maNguoiThue +
                ", ten='" + ten + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
