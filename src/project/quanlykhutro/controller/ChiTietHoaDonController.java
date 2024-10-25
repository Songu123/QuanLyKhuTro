package project.quanlykhutro.controller;

import project.ctdl.DoublyLinkedListChiTietHoaDon;
import project.ctdl.DoublyLinkedListDichVu;
import project.ctdl.DoublyLinkedListHoaDon;
import project.ctdl.NodeDichVu;
import project.quanlykhutro.models.ChiTietHoaDon;

import java.util.Scanner;

public class ChiTietHoaDonController {
    public static DoublyLinkedListDichVu listDichVu = new DoublyLinkedListDichVu();
    public static DoublyLinkedListChiTietHoaDon listChiTietHoaDon = new DoublyLinkedListChiTietHoaDon();
    public static Scanner sc = new Scanner(System.in);

    public static ChiTietHoaDon nhapSoLuongDichVu(NodeDichVu dichVu, int maHoaDon) {
        ChiTietHoaDon chiTietHoaDon;
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
        chiTietHoaDon = new ChiTietHoaDon(maHoaDon, maDichVu, soLuong, donGia, thanhTien);
        return chiTietHoaDon;
    }

    public static void nhapChiTietHoaDon(int maHoaDon) {
        listDichVu.duyetListDichVu(maHoaDon);
    }

    public static void hienThiChiTietHoaDon() {
        int maHoaDon = 0;
        while (true) {
            try {
                System.out.println("Nhập mã Hoá Đơn: ");
                maHoaDon = Integer.parseInt(sc.nextLine());
                if (checkHoaDon(maHoaDon)) {
                    break;
                }
                System.out.println("Nhập mã hoá đơn sai! Vui lòng nhập mã hoá đơn khác");
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số hợp lệ cho mã hoá đơn!");
            } catch (Exception e) {
                System.out.println("Lỗi không xác định: " + e.getMessage());
            }
        }
        listChiTietHoaDon.printListChiTietHoaDonWithID(maHoaDon);
    }

    public static boolean checkHoaDon(int maHoaDon) {
        return listChiTietHoaDon.checkHoaDon(maHoaDon) ;
    }

}
