package project.quanlykhutro.models;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

public class DichVu {
    private int maDichVu;
    private String tenDichVu;
    private float donGia;
    private String trangThai;

    public DichVu(String tenDichVu, float donGia, String trangThai) {
        this.tenDichVu = tenDichVu;
        this.donGia = donGia;
        this.trangThai = trangThai;
    }

    public DichVu() {
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(int maDichVu) {
        this.maDichVu = maDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }
}
