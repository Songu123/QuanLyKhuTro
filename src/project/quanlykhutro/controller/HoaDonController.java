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
            System.out.print("Vui lòng chọn chức năng (0-5): ");
            int chon = 0;
            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
                continue;
            }

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
                    thongKeNo();
                    break;
                case 0:
                    System.out.println("Đã thoát quản lý hóa đơn.");
                    return;
                default:
                    System.out.println("Nhập sai! Vui lòng nhập lại (0-5)!");
            }
        }
    }

    // Nhập thông tin hoá đơn
    public static HoaDon nhapThongTin() {
        HoaDon hoaDon;
        int maHopDong = 0;
        LocalDate ngayPhatHanh = LocalDate.now();
        LocalDate ngayDenHan = ngayPhatHanh.plusDays(5);  // Ngày đến hạn là ngày phát hành + 5 ngày
        float tongTien = 0;
        String trangThai = "Chưa Thanh Toán";

        HopDongController.printListHopDong();

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
            }
        }


        // Tạo đối tượng hóa đơn sau khi nhập thành công
        hoaDon = new HoaDon(maHopDong, ngayPhatHanh, ngayDenHan, tongTien, trangThai);
        return hoaDon;
    }

    public static void addHoaDon() {
        try {
            HoaDon hoaDon = nhapThongTin();
            HoaDonService.addHoaDon(hoaDon);
            ChiTietHoaDonController.nhapChiTietHoaDon(HoaDonService.getIdLastRow());

            updateHoaDon(HoaDonService.getLastRow());
//            hoaDon.setTrangThai(nhapTrangThai());
//            hoaDon.setTongTien(tinhTongTien(HoaDonService.getIdLastRow(), hoaDon.getMaHopDong()));
//            HoaDonService.capNhatThongTinHoaDon(HoaDonService.getIdLastRow(), hoaDon.getNgayDenHan(), hoaDon.getTongTien(), hoaDon.getTrangThai());
            System.out.println("Đã nhập thành công thông tin hóa đơn!");

            listHoaDon.addHoaDon(HoaDonService.getLastRow());
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm hóa đơn: " + e.getMessage());
        }
    }

    public static void searchHoaDon() {
        try {
            System.out.print("Nhập ID hóa đơn cần tìm kiếm: ");
            int id = Integer.parseInt(sc.nextLine());
            listHoaDon.searchHoaDon(id);
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteHoaDon() {
        try {
            System.out.print("Nhập ID hóa đơn muốn xoá: ");
            int id = Integer.parseInt(sc.nextLine());
            listHoaDon.deleteHoaDon(id);
            HoaDonService.deleteHoaDon(id);
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateTrangThaiHoaDon() {
        try {
            System.out.print("Nhập ID hóa đơn muốn cập nhật: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Nhập trạng thái (1 - Đã thanh Toán, 2 - Chưa Thanh Toán, 3 - Quá Hạn): ");

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
                    return;
            }
            listHoaDon.updateHoaDon(id, trangThai);
            HoaDonService.updateTrangThaiHoaDon(trangThai, id);
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printListHoaDon() {
        try {
            if (listHoaDon.getSize() > 0) {
                listHoaDon.printListHoaDon();
            } else {
                System.out.println("Danh sách hóa đơn rỗng!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static float tinhTongTien(int maHoaDon, int maHopDong) {
        System.out.println("Ma Hoá đơn " + maHoaDon);
        System.out.println("Tổng tiền thanh toán chi tiết dịch vuj: " + ChiTietHoaDonController.tinhTongThanhToan(maHoaDon));
        System.out.println("Tiền thuê phòng: " + HopDongController.getGiaThue(maHopDong));
        return ChiTietHoaDonController.tinhTongThanhToan(maHoaDon) + HopDongController.getGiaThue(maHopDong);
    }

    // Hàm nhập ngày đến hạn
//    public static LocalDate nhapNgayDenHan(LocalDate ngayPhatHanh) {
//        LocalDate ngayDenHan = null;
//        while (true) {
//            try {
//                System.out.print("Nhập ngày đến hạn (định dạng: yyyy-MM-dd): ");
//                String inputNgayDenHan = sc.nextLine();
//                ngayDenHan = LocalDate.parse(inputNgayDenHan, formatter);
//
//                if (ngayDenHan.isAfter(ngayPhatHanh)) {
//                    break;
//                } else {
//                    System.out.println("Lỗi: Ngày đến hạn phải sau ngày phát hành!");
//                }
//            } catch (DateTimeParseException e) {
//                System.out.println("Lỗi: Vui lòng nhập ngày theo định dạng yyyy-MM-dd!");
//            }
//        }
//        return ngayDenHan;
//    }

    // Hàm nhập trạng thái thanh toán
    public static String nhapTrangThai() {
        String trangThai = "";
        try {
            System.out.print("Nhập trạng thái (1 - Đã thanh Toán, 2 - Chưa Thanh Toán): ");

            int chon = Integer.parseInt(sc.nextLine());
            switch (chon) {
                case 1:
                    trangThai = "Đã Thanh Toán";
                    break;
                case 2:
                    trangThai = "Chưa Thanh Toán";
                    break;

                default:
                    System.out.println("Nhập sai! Nhập (1 hoặc 2)!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return trangThai;
    }

    // Hàm cập nhật thông tin hoá đơn
    public static void updateHoaDon(HoaDon hoaDon) {
//        hoaDon.setNgayDenHan(hoaDon.getNgayPhatHanh());
        hoaDon.setTrangThai(nhapTrangThai());
        hoaDon.setTongTien(tinhTongTien(hoaDon.getMaHoaDon(), hoaDon.getMaHopDong()));
        HoaDonService.capNhatThongTinHoaDon(HoaDonService.getIdLastRow(), hoaDon.getNgayDenHan(), hoaDon.getTongTien(), hoaDon.getTrangThai());
    }

    // Hàm hiển thị chi tiết hoá đơn
    public static void hienThiChiTietHoaDon() {
        ChiTietHoaDonController.hienThiChiTietHoaDon();
    }

    // Hàm thống kê nợ
    public static void thongKeNo() {
        // Implement chức năng thống kê nợ nếu cần
        System.out.println("Chức năng thống kê nợ đang được phát triển.");
    }
}

