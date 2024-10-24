package project.quanlykhutro.controller;

import project.ctdl.DoublyLinkedListChiTietHoaDon;
import project.ctdl.DoublyLinkedListDichVu;
import project.ctdl.NodeDichVu;
import project.quanlykhutro.models.ChiTietHoaDon;
import project.quanlykhutro.models.DichVu;

import java.util.Scanner;

public class ChiTietHoaDonController {
    public static DoublyLinkedListDichVu listDichVu = new DoublyLinkedListDichVu();
    public static DoublyLinkedListChiTietHoaDon listChiTietHoaDon = new DoublyLinkedListChiTietHoaDon();
    public static Scanner sc = new Scanner(System.in);

    public static ChiTietHoaDon nhapSoLuongDichVu(NodeDichVu dichVu) {
        float soLuong;
        while (true) {
            try {
                System.out.println("Nhập số lượng " + dichVu.data.getTenDichVu() + ": ");
                soLuong = Float.parseFloat(sc.nextLine());
                if (soLuong > 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        int maDichVu = dichVu.data.getMaDichVu();
        float donGia = dichVu.data.getDonGia();
        float thanhTien = soLuong * donGia;
        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(maDichVu, soLuong, donGia, thanhTien);
        return chiTietHoaDon;
    }

    public static void addChiTietHoaDon() {
        listDichVu.duyetListDichVu();
    }

}
