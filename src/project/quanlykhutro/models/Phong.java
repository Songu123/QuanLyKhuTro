package project.quanlykhutro.models;

public class Phong {
    private int maPhong;
    private float dienTich;
    private float giaThue;
    private String trangThai;
    private String moTa;
    private int maQuanLy;

    public Phong() {
    }

    public Phong(float dienTich, float giaThue, String trangThai, String moTa, int maQuanLy) {
        this.dienTich = dienTich;
        this.giaThue = giaThue;
        this.trangThai = trangThai;
        this.moTa = moTa;
        this.maQuanLy = maQuanLy;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public float getDienTich() {
        return dienTich;
    }

    public void setDienTich(float dienTich) {
        this.dienTich = dienTich;
    }

    public float getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(float giaThue) {
        this.giaThue = giaThue;
    }

    public String getTrangThai() {
       return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getMaQuanLy() {
        return maQuanLy;
    }

    public void setMaQuanLy(int maQuanLy) {
        this.maQuanLy = maQuanLy;
    }

    @Override
    public String toString() {
        return "Phong{" +
                "maPhong=" + maPhong +
                ", dienTich=" + dienTich +
                ", giaThue=" + giaThue +
                ", trangThai='" + trangThai + '\'' +
                ", moTa='" + moTa + '\'' +
                ", maQuanLy=" + maQuanLy +
                '}';
    }
}
