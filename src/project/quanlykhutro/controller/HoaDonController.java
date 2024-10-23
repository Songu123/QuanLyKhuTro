package project.quanlykhutro.controller;

import project.ctdl.DoublyLinkedListHoaDon;
import project.quanlykhutro.models.HoaDon;
import project.quanlykhutro.services.HoaDonService;
import project.quanlykhutro.services.HopDongService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class HoaDonController {
    public static Scanner sc = new Scanner(System.in);
    public static DoublyLinkedListHoaDon listHoaDon = new DoublyLinkedListHoaDon();

    public static HoaDon nhapThongTin() {
        HoaDon hoaDon;
        int maHopDong = 0;
        LocalDate ngayPhatHanh = null, ngayDenHan = null;
        float tongTien = 0;
        String trangThai = "";
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Nhập mã hợp đồng
        while (true) {
            try {
                System.out.println("Nhập mã hợp đồng: ");
                maHopDong = Integer.parseInt(sc.nextLine());

                if (maHopDong > 0 && HoaDonService.checkMaHopDong(maHopDong)) { // Giả sử checkMaHopDong() kiểm tra mã hợp đồng có hợp lệ không
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

        // Nhập ngày phát hành
        while (true) {
            try {
                System.out.println("Nhập ngày phát hành (định dạng: yyyy-MM-dd): ");
                String inputNgayPhatHanh = sc.nextLine();
                ngayPhatHanh = LocalDate.parse(inputNgayPhatHanh, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Lỗi: Vui lòng nhập ngày theo định dạng yyyy-MM-dd!");
            }
        }

        // Nhập ngày đến hạn
        while (true) {
            try {
                System.out.println("Nhập ngày đến hạn (định dạng: yyyy-MM-dd): ");
                String inputNgayDenHan = sc.nextLine();
                ngayDenHan = LocalDate.parse(inputNgayDenHan, formatter);

                if (ngayDenHan.isAfter(ngayPhatHanh)) {  // Kiểm tra xem ngày đến hạn có sau ngày phát hành không
                    break;
                } else {
                    System.out.println("Lỗi: Ngày đến hạn phải sau ngày phát hành!");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Lỗi: Vui lòng nhập ngày theo định dạng yyyy-MM-dd!");
            }
        }

        // Nhập tổng tiền
        while (true) {
            try {
                System.out.println("Nhập tổng tiền: ");
                tongTien = Float.parseFloat(sc.nextLine());

                if (tongTien > 0) {
                    break;
                } else {
                    System.out.println("Lỗi: Tổng tiền phải lớn hơn 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ cho tổng tiền!");
            }
        }

        // Nhập trạng thái
        while (true) {
            try {
                System.out.println("Nhập trạng thái (Đã thanh toán/Chưa thanh toán): ");
                trangThai = sc.nextLine().trim();

                if (trangThai.equalsIgnoreCase("Đã thanh toán") || trangThai.equalsIgnoreCase("Chưa thanh toán")) {
                    break;
                } else {
                    System.out.println("Lỗi: Trạng thái không hợp lệ! Vui lòng nhập 'Đã thanh toán' hoặc 'Chưa thanh toán'.");
                }
            } catch (Exception e) {
                System.out.println("Lỗi không xác định: " + e.getMessage());
            }
        }

        // Tạo đối tượng hóa đơn sau khi nhập thành công
        System.out.println("Đã nhập thành công thông tin hóa đơn!");
        hoaDon = new HoaDon(maHopDong, ngayPhatHanh, ngayDenHan, tongTien, trangThai);
        System.out.println(hoaDon);
        return hoaDon;
    }


    public static void addHoaDon() {
        try {
            HoaDon hoaDon = nhapThongTin();
            HoaDonService.addHoaDon(hoaDon);
            listHoaDon.addHoaDon(HoaDonService.getLastRow());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public void hienThiThongTin() {
//        System.out.printf("| %-8s | %-20s | %-8s | %-8s | %-8s |\n", getMaHoaDon(), getDienTich(), getGiaThue(), getTrangThai(), getMoTa(), getMaQuanLy());
//    }

    // Hàm hiển thị tiêu đề dịch vụ
    public static void hienThiTieuDeHoaDon() {
        System.out.println("+------------+----------------------+------------+");
        System.out.printf("| %-10s | %-20s | %-10s |\n",
                "MaHoaDon", "TenHoaDon", "DonGia");
        System.out.println("+------------+----------------------+------------+");
    }

    public static void searchHoaDon() {
        int id = 0;
        try {
            System.out.println("Nhập ID phòng tìm kiếm: ");
            id = Integer.parseInt(sc.nextLine());
            hienThiTieuDeHoaDon();
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
                hienThiTieuDeHoaDon();
                listHoaDon.printListHoaDon();
            } else {
                System.out.println("Danh sách phòng rỗng!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
