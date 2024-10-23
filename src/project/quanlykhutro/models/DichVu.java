package project.quanlykhutro.models;

public class DichVu {
    private int maDichVu;
    private String tenDichVu;
    private float donGia;

    public DichVu(String tenDichVu, float donGia) {
        this.tenDichVu = tenDichVu;
        this.donGia = donGia;
    }

    public DichVu() {
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
