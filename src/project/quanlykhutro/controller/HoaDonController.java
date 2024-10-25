package project.quanlykhutro.controller;

import project.ctdl.DoublyLinkedListChiTietHoaDon;
import project.ctdl.DoublyLinkedListHoaDon;
import project.quanlykhutro.models.HoaDon;
import project.quanlykhutro.services.HoaDonService;
import project.quanlykhutro.services.HopDongService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class HoaDonController {
    public static Scanner sc = new Scanner(System.in);
    public static DoublyLinkedListHoaDon listHoaDon = new DoublyLinkedListHoaDon();
    public static DoublyLinkedListChiTietHoaDon listChiTietHoaDon = new DoublyLinkedListChiTietHoaDon();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void run() {
        while (true) {
            Menu.menuQuanLyHoaDon();
            System.out.println("Vui lòng chọn chức năng (0-4): ");
            int chon = sc.nextInt();
            sc.nextLine();
            switch (chon) {
                case 1:
                    addHoaDon();
                    break;
                case 2:
                    hienThiChiTietHoaDon();
                    break;
                case 3:
                    updateTrangThaiHoaDon();
                    break;
                case 4:
                    printListHoaDon();
                    break;
                case 5:

                    break;

                case 0:
                    break;
                default:
                    System.out.println("Nhập sai! Vui lòng nhập lại (0-6)!");
            }
        }
    }

    //    Nhập thông tin hoá đơn
    public static HoaDon nhapThongTin() {
        HoaDon hoaDon;
        int maHopDong = 0;
        LocalDate ngayPhatHanh = LocalDate.now();  // Lấy ngày phát hành hiện tại
        LocalDate ngayDenHan = null;
        float tongTien = 0;
        String trangThai = "";

        // Nhập mã hợp đồng
        while (true) {
            try {
                System.out.print("Nhập mã hợp đồng: ");
                maHopDong = Integer.parseInt(sc.nextLine());

                if (maHopDong > 0 && HoaDonService.checkMaHopDong(maHopDong)) {
                    break;
                } else {
                    System.out.println("Lỗi: Mã hợp đồng không tồn tại! Vui lòng nhập lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ cho mã hợp đồng!");
            } catch (Exception e) {
                System.out.println("Lỗi không xác định: " + e.getMessage());
            }
        }



        // Tạo đối tượng hóa đơn sau khi nhập thành công
        hoaDon = new HoaDon(maHopDong, ngayPhatHanh, ngayDenHan, tongTien, trangThai);
        System.out.println(hoaDon);
        return hoaDon;
    }

    public static void addHoaDon() {
        try {
            HoaDon hoaDon = nhapThongTin();
            HoaDonService.addHoaDon(hoaDon);
            ChiTietHoaDonController.nhapChiTietHoaDon(HoaDonService.getIdLastRow());

            updateHoaDon(hoaDon);
            System.out.println("Đã nhập thành công thông tin hóa đơn!");

            listHoaDon.addHoaDon(HoaDonService.getLastRow());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void searchHoaDon() {
        int id = 0;
        try {
            System.out.println("Nhập ID phòng tìm kiếm: ");
            id = Integer.parseInt(sc.nextLine());
            listHoaDon.searchHoaDon(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteHoaDon() {
        try {
            System.out.println("Nhập ID Phòng muốn xoá: ");
            int id = Integer.parseInt(sc.nextLine());

            listHoaDon.deleteHoaDon(id);
            HoaDonService.deleteHoaDon(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void updateTrangThaiHoaDon() {
        try {
            System.out.println("Nhập ID Phòng muốn cập nhật: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.println("Nhập trạng thái (1 - Đã thanh Toán, 2 - Chưa Thanh Toán, 3 - Quá Hạn): ");

            String trangThai = "";
            int chon = Integer.parseInt(sc.nextLine());
            switch (chon) {
                case 1:
                    trangThai = "Đã Thanh Toán";
                    break;
                case 2:
                    trangThai = "Chưa Thanh Toán";
                    break;
                case 3:
                    trangThai = "Quá Hạn";
                    break;
                default:
                    System.out.println("Nhập sai! Nhập (1, 2 hoặc 3)!");
            }
            listHoaDon.updateHoaDon(id, trangThai);
            HoaDonService.updateTrangThaiHoaDon(trangThai, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void printListHoaDon() {
        try {
            if (listHoaDon.getSize() > 0) {
                listHoaDon.printListHoaDon();
            } else {
                System.out.println("Danh sách phòng rỗng!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static float tinhTongTien(int maHoaDon) {
        return listChiTietHoaDon.tinhTongTien(maHoaDon);
    }

    // Hàm nhập ngày đến hạn
    public static LocalDate nhapNgayDenHan(LocalDate ngayPhatHanh) {
        LocalDate ngayDenHan = null;
        while (true) {
            try {
                System.out.print("Nhập ngày đến hạn (định dạng: yyyy-MM-dd): ");
                String inputNgayDenHan = sc.nextLine();
                ngayDenHan = LocalDate.parse(inputNgayDenHan, formatter);

                if (ngayDenHan.isAfter(ngayPhatHanh)) {
                    break;
                } else {
                    System.out.println("Lỗi: Ngày đến hạn phải sau ngày phát hành!");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Lỗi: Vui lòng nhập ngày theo định dạng yyyy-MM-dd!");
            }
        }
        return ngayDenHan;
    }

    //    Hàm nhập trạng thái thanh toán
    public static String nhapTrangThai() {
        String trangThai = "Chưa thanh toán";
        while (true) {
            System.out.print("Nhập trạng thái (Đã thanh toán/Chưa thanh toán): ");
            trangThai = sc.nextLine().trim();

            if (trangThai.equalsIgnoreCase("Đã thanh toán") || trangThai.equalsIgnoreCase("Chưa thanh toán")) {
                break;
            } else {
                System.out.println("Lỗi: Trạng thái không hợp lệ! Vui lòng nhập 'Đã thanh toán' hoặc 'Chưa thanh toán'.");
            }
        }
        return trangThai;
    }

    //    Hàm cập nhật thông tin hoá đơn
    public static void updateHoaDon(HoaDon hoaDon) {
        hoaDon.setNgayDenHan(hoaDon.getNgayPhatHanh());
        hoaDon.setTrangThai(nhapTrangThai());
        hoaDon.setTongTien(tinhTongTien(hoaDon.getMaHoaDon()));
        HoaDonService.capNhatThongTinHoaDon(HoaDonService.getIdLastRow(), hoaDon.getNgayDenHan(), hoaDon.getTongTien(), hoaDon.getTrangThai());
    }

    //    Hàm hiển thị chi tiết hoá đơn
    public static void hienThiChiTietHoaDon() {
        ChiTietHoaDonController.hienThiChiTietHoaDon();
    }

    //  Hàm thống kê nợ
    public static void thongKeNo() {

    }

}
