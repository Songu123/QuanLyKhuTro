package project.quanlykhutro.controller;

import project.ctdl.*;
import project.quanlykhutro.models.*;
import project.quanlykhutro.services.*;

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
                System.out.println("Số lượng nhập sai! Vui lòng nhập số lượng hợp lệ!");
                System.out.println(e.getMessage());
            }
        }
        int maDichVu = dichVu.data.getMaDichVu();
        float donGia = dichVu.data.getDonGia();
        float thanhTien = soLuong * donGia;
        chiTietHoaDon = new ChiTietHoaDon(maHoaDon, maDichVu, soLuong, donGia, thanhTien);
//        Thêm nó vào cơ sở dữ liệu
        ChiTietHoaDonService.addChiTietHoaDon(chiTietHoaDon);
//        Lấy dữ liệu đưa vào danh sách
        listChiTietHoaDon.addChiTietHoaDon(chiTietHoaDon);
        return chiTietHoaDon;
    }

    public static void nhapChiTietHoaDon(int maHoaDon) {
        listDichVu.duyetListDichVu(maHoaDon);
//        listChiTietHoaDon.printListChiTietHoaDon();
    }

    public void hienThiDanhSachChiTietHoaDon() {
        listChiTietHoaDon.printListChiTietHoaDon();
    }


    public static void hienThiChiTietHoaDon() {
        int maHoaDon = 0;
        while (true) {
            try {
                System.out.println("Nhập mã Hoá Đơn: ");
                maHoaDon = Integer.parseInt(sc.nextLine());
                if (checkHoaDon(maHoaDon)) {
                    HoaDon hoaDon = HoaDonService.getHoaDonById(maHoaDon);
                    HopDong hopDong = HopDongService.getHopDongById(hoaDon.getMaHopDong());
                    Phong phong = PhongService.getPhongById(hopDong.getMaPhong());
                    NguoiThue nguoiThue = NguoiThueService.getNguoiThueById(hopDong.getMaNguoiThue());
                    System.out.println("+-----------------------------------------------------------+");
                    System.out.println("|                      Chi Tiết Hoá Đơn                     |");
                    System.out.println("+-----------------------------------------------------------+");
                    System.out.println("Từ ngày: " + hoaDon.getNgayPhatHanh() + " đến " + hoaDon.getNgayDenHan());
                    System.out.println("Mã phòng: " + phong.getMaPhong());
                    System.out.println("Diện tích: " + phong.getDienTich() + " m2");

// Chuyển đổi giá trị của giá thuê thành `float` hoặc `double` trước khi in
                    try {
                        float giaThue = Float.parseFloat(String.valueOf(phong.getGiaThue()));
                        System.out.printf("Giá thuê: %,10.2f VND\n", giaThue); // Sử dụng định dạng với dấu phẩy ngăn cách hàng nghìn
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi: Giá trị của giá thuê không hợp lệ.");
                    }

                    System.out.println("Mã người thuê: " + nguoiThue.getMaNguoiThue());
                    System.out.println("Họ tên: " + nguoiThue.getTen());
                    System.out.println("Ngày sinh: " + nguoiThue.getNgaySinh());
                    System.out.println("Giới tính: " + nguoiThue.getGioiTinh());
                    System.out.println("Địa chỉ: " + nguoiThue.getDiaChi());
                    System.out.println("Số điện thoại: " + nguoiThue.getSoDienThoai());
                    System.out.printf("Tổng tiền: %,10.2f VND\n", tinhTongThanhToan(maHoaDon));
                    System.out.println("+-----------------------------------------------------------+");


                    listChiTietHoaDon.printListChiTietHoaDonWithID(maHoaDon);
                    break;
                }
                System.out.println("Nhập mã hoá đơn sai! Vui lòng nhập mã hoá đơn khác");
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số hợp lệ cho mã hoá đơn!");
            } catch (Exception e) {
                System.out.println("Lỗi không xác định: " + e.getMessage());
            }
        }

    }

    public static boolean checkHoaDon(int maHoaDon) {
        return listChiTietHoaDon.checkHoaDon(maHoaDon) ;
    }

    public static float tinhTongThanhToan(int maHoaDon) {
        return listChiTietHoaDon.tinhTongTien(maHoaDon);
    }

}
