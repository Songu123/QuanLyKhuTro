package project.quanlykhutro.controller;

import project.quanlykhutro.models.DoanhThu;
import project.quanlykhutro.models.NguoiThue;
import project.quanlykhutro.services.UserService;

import java.util.Scanner;

public class QuanLyKhuTroController {
    public static Scanner sc = new Scanner(System.in);

    public static void run() {
        boolean running = true;
        while (running) {
            Menu.menuQuanLyKhuTro();
            System.out.println("Vui lòng chọn chức năng (0-6): ");
            try {
                int chon = Integer.parseInt(sc.nextLine());
                switch (chon) {
                    case 1:
                        PhongController.run();
                        break;
                    case 2:
                        NguoiThueController.run();
                        break;
                    case 3:
                        DichVuController.run();
                        break;
                    case 4:
                        HopDongController.run();
                        break;
                    case 5:
                        HoaDonController.run();
                        break;
                    case 6:
                        menuBaoCaoThongKe();
                        break;
                    case 0:
                        running = false;
                    default:
                        System.out.println("Nhập sai! Vui lòng nhập lại (0-6)!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ.");
            }

        }
    }

    public static void logIn() {
        while (true) {
            System.out.println("Vui lòng nhập tài khoản: ");
            String username = sc.nextLine();
            System.out.println("Vui nhập mật khẩu: ");
            String password = sc.nextLine();
            if (UserService.checkLogin(username, password)) {
                System.out.println("CHÀO MỪNG ADMIN!!!!");
                run();
                break;
            } else {
                System.out.println("Tài khoản hoặc mật khẩu không tồn tại! Vui lòng nhập lại ");
            }
        }
    }

    public static void menuBaoCaoThongKe() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("+---------------------------------------------------+");
            System.out.println("|               BÁO CÁO DOANH THU & THỐNG KÊ        |");
            System.out.println("+---------------------------------------------------+");
            System.out.println("| 1. Xem báo cáo doanh thu theo tháng               |");
            System.out.println("| 2. Xem báo cáo doanh thu theo năm                 |");
            System.out.println("| 3. Thống kê số phòng trống                        |");
            System.out.println("| 4. Thống kê số phòng đã thuê                      |");
            System.out.println("| 5. Thống kê số phòng đang bảo trì                 |");
            System.out.println("| 0. Thoát                                          |");
            System.out.println("+---------------------------------------------------+");

            System.out.print("Chọn chức năng (0-6): ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    xemBaoCaoDoanhThuTheoThang();
                    break;
                case 2:
                    xemBaoCaoDoanhThuTheoNam();
                    break;
                case 3:
                    thongKePhongTrong();
                    break;
                case 4:
                    thongKePhongDaThue();
                    break;
                case 5:
                    thongKePhongBaoTri();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 0);
    }

    // Các phương thức xử lý các lựa chọn
    public static void xemBaoCaoDoanhThuTheoThang() {
        System.out.println("+------------+-----------------------+-------------------+-----------------------------+--------------------------------+----------------------------+");
        System.out.println("|   Tháng    |  Tổng Doanh Thu (VNĐ) | Số Lượng Hóa Đơn |  Số Tiền Đã Thanh Toán (VNĐ) |  Số Tiền Chưa Thanh Toán (VNĐ) | % Doanh Thu Đã Thanh Toán  |");
        System.out.println("+------------+-----------------------+-------------------+-----------------------------+--------------------------------+----------------------------+");

        for (int i = 1; i <= 12; i++) {
            DoanhThu doanhThu = HoaDonController.getHoaDonByThang(i);
            System.out.printf("| %-10s | %,21.0f | %-17d | %,27.0f | %,30.0f | %-26.1f%% |\n",
                    doanhThu.getThang(), doanhThu.getTongDoanhThu(), doanhThu.getSoLuongHoaDon(),
                    doanhThu.getSoTienDaThanhToan(), doanhThu.getSoTienChuaThanhToan(), doanhThu.getPhanTramDaThanhToan());
        }

        System.out.println("+------------+-----------------------+-------------------+-----------------------------+--------------------------------+----------------------------+");
    }



    public static void xemBaoCaoDoanhThuTheoNam() {
        // Xử lý xem báo cáo doanh thu theo năm
        System.out.println("Xem báo cáo doanh thu theo năm.");
    }
    public static void thongKePhongTrong() {
        // Xử lý thống kê số phòng trống
        System.out.println("Thống kê số phòng trống.");
        PhongController.printListPhongWithStatus("Phòng Trống");
    }

    public static void thongKePhongDaThue() {
        // Xử lý thống kê số phòng đã thuê
        System.out.println("Thống kê số phòng đã thuê.");
        PhongController.printListPhongWithStatus("Có Người");
    }

    public static void thongKePhongBaoTri() {
        // Xử lý thống kê số phòng đang bảo trì
        System.out.println("Thống kê số phòng đang bảo trì.");
        PhongController.printListPhongWithStatus("Đang Bảo Trì");
    }

}
