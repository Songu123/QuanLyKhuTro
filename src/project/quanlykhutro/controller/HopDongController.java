package project.quanlykhutro.controller;

import project.ctdl.DoublyLinkedListHopDong;
import project.ctdl.DoublyLinkedListPhong;
import project.quanlykhutro.models.HopDong;
import project.quanlykhutro.models.HopDong;
import project.quanlykhutro.models.NguoiThue;
import project.quanlykhutro.models.Phong;
import project.quanlykhutro.services.HopDongService;
import project.quanlykhutro.services.HopDongService;
import project.quanlykhutro.services.NguoiThueService;
import project.quanlykhutro.services.PhongService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class HopDongController {
    public static DoublyLinkedListHopDong listHopDong = new DoublyLinkedListHopDong();
    public static Scanner sc = new Scanner(System.in);

    public static void run() {
        while (true) {
            Menu.menuQuanLyHopDong();
            System.out.println("Vui lòng chọn chức năng (0-6): ");
            int chon;
            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
                continue;
            }
            switch (chon) {
                case 1:
                    addHopDong();
                    break;
                case 2:
                    printListHopDong();
                    break;
                case 3:
                    giaHanHopDong();
                    break;
                case 4:
                    searchHopDong();
                    break;
                case 5:
                    updateHopDong();
                    break;
                case 6:
                    deleteHopDong();
                    break;
                case 7:

                    break;
                case 8:
                    xemChiTietHopDong();
                    break;
                case 0:
                    System.out.println("Bạn đã thoát quản lý hợp đồng");
                    return;
                default:
                    System.out.println("Nhập sai! Vui lòng nhập lại (0-6)!");
            }
        }
    }

    public static HopDong nhapThongTin() {
        HopDong hopDong;
        Scanner sc = new Scanner(System.in);
        int maPhong = -1;
        int maNguoiThue = -1;

        // Kiểm tra phòng trống
        if (PhongController.checkPhongTrong()) {
            System.out.println("Phòng còn trống, xin mời tiếp tục!");
        } else {
            System.out.println("Phòng đã hết! Vui lòng thông cảm!");
            return null; // Kết thúc nếu không còn phòng trống
        }

        // Nhập mã phòng
        while (true) {
            try {
                PhongController.printListPhong();
                System.out.print("Nhập mã phòng: ");
                maPhong = Integer.parseInt(sc.nextLine());
                if (checkPhong(maPhong)) {
                    System.out.println("Phòng này trống! Mời bạn tiếp tục");
                    break;
                } else {
                    System.out.println("Phòng này có người hoặc không tồn tại! Vui lòng chọn phòng khác!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ cho mã phòng!");
            } catch (Exception e) {
                System.out.println("Lỗi không xác định: " + e.getMessage());
            }
        }

        // Nhập mã người thuê
        while (true) {
            try {
                NguoiThueController.printListNguoiThue();
                System.out.print("Nhập mã người thuê: ");
                maNguoiThue = Integer.parseInt(sc.nextLine());

                // Kiểm tra mã người thuê có tồn tại và hợp lệ
                if (maNguoiThue > 0 && HopDongService.checkMaNguoiThue(maNguoiThue)) {

                    // Kiểm tra người thuê đã có phòng chưa hoặc đang ở trạng thái chờ
                    if (NguoiThueController.checkNguoiThue(maNguoiThue)) {
                        System.out.println("Người thuê hợp lệ! Tiếp tục với hợp đồng.");
                        break;
                    } else {
                        System.out.println("Người thuê đã thuê phòng! Không thể thuê phòng khác.");
                    }
                } else {
                    System.out.println("Lỗi: Mã người thuê không tồn tại hoặc không hợp lệ! Vui lòng chọn lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ cho mã người thuê!");
            } catch (Exception e) {
                System.out.println("Lỗi không xác định: " + e.getMessage());
            }
        }


        // Nhập ngày bắt đầu và ngày kết thúc với yêu cầu thuê tối thiểu 3 tháng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ngayBatDau = LocalDate.now();
        LocalDate ngayKetThuc = null;

        while (true) {
            try {
                System.out.print("Nhập ngày kết thúc (định dạng: yyyy-MM-dd): ");
                ngayKetThuc = LocalDate.parse(sc.nextLine(), formatter);

                if (ngayKetThuc.isBefore(ngayBatDau.plusMonths(3))) {
                    System.out.println("Lỗi: Thời hạn thuê phải từ 3 tháng trở lên kể từ ngày hiện tại! Vui lòng nhập lại.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Lỗi: Vui lòng nhập ngày theo định dạng yyyy-MM-dd!");
            }
        }

        // Lấy giá thuê phòng từ ID phòng
        float giaThue = PhongController.getPhongWithId(maPhong).getGiaThue();

        // Nhập tiền cọc
        float tienCoc = -1;
        while (true) {
            try {
                System.out.print("Nhập tiền cọc: ");
                tienCoc = Float.parseFloat(sc.nextLine());
                if (tienCoc > 0) {
                    break;
                } else {
                    System.out.println("Tiền cọc không hợp lệ! Vui lòng nhập số lớn hơn 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ cho tiền cọc!");
            }
        }

        // Tạo hợp đồng mới và thông báo
        hopDong = new HopDong(maPhong, maNguoiThue, ngayBatDau, ngayKetThuc, giaThue, tienCoc);
        System.out.println("Đã nhập thành công thông tin hợp đồng!");
        System.out.println(hopDong);

        return hopDong;
    }


    public static void addHopDong() {
        try {
            HopDong hopDong = nhapThongTin();
            assert hopDong != null;
            System.out.println("Ma phong: " + hopDong.getMaPhong());
            HopDongService.addHopDong(hopDong);
            listHopDong.addLast(HopDongService.getLastRow());
            PhongController.updatePhongWithHopDong(HopDongService.getLastRow().getMaPhong());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean checkPhong(int maPhong) {
        Phong phong = PhongService.getPhongById(maPhong);
        return phong.getTrangThai().compareToIgnoreCase("Trống") == 0;
    }

    public static void hienThiTieuDeHopDong() {
        System.out.println("+------------+------------+--------------+------------+------------+--------------+------------+");
        System.out.printf("| %-10s | %-10s | %-12s | %-10s | %-10s | %-12s | %-10s |\n",
                "MaHD", "MaPhong", "MaNguoiThue", "NgayBD", "NgayKT", "GiaThue", "TienCoc");
        System.out.println("+------------+------------+--------------+------------+------------+--------------+------------+");
    }

    public static void searchHopDong() {
        int id = 0;
        try {
            System.out.println("Nhập ID phòng tìm kiếm: ");
            id = Integer.parseInt(sc.nextLine());
            hienThiTieuDeHopDong();
            listHopDong.searchHopDong(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteHopDong() {
        try {
            System.out.println("Nhập ID Hợp Đồng muốn xoá: ");
            int id = Integer.parseInt(sc.nextLine());

            listHopDong.deleteHopDong(id);
            HopDongService.deleteHopDong(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void updateHopDong() {
        try {
            System.out.println("Nhập ID Hợp Đồng muốn cập nhật: ");
            int id = Integer.parseInt(sc.nextLine());

            HopDong nguoiThue = nhapThongTin();
            listHopDong.updateHopDong(id, nguoiThue);
            HopDongService.updateHopDong(nguoiThue, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void printListHopDong() {
        try {
            if (listHopDong.getSize() > 0) {
                hienThiTieuDeHopDong();
                listHopDong.printListHopDong();
            } else {
                System.out.println("Danh sách phòng rỗng!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void giaHanHopDong() {
        Scanner sc = new Scanner(System.in);
        int maHopDong = -1;

        // Xử lý lỗi nhập mã hợp đồng
        while (true) {
            try {
                System.out.println("Nhập mã hợp đồng cần gia hạn: ");
                maHopDong = Integer.parseInt(sc.nextLine());
                break; // Thoát khỏi vòng lặp khi mã hợp đồng hợp lệ
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ cho mã hợp đồng!");
            }
        }

        // Tìm hợp đồng dựa trên mã
        HopDong hopDong = HopDongService.getHopDongById(maHopDong);
        if (hopDong == null) {
            System.out.println("Hợp đồng không tồn tại!");
            return;
        }

        // Nhập thời gian gia hạn (số tháng)
        int thangGiaHan = -1;
        while (true) {
            try {
                System.out.println("Nhập thời gian gia hạn (số tháng): ");
                thangGiaHan = Integer.parseInt(sc.nextLine());

                // Kiểm tra thời gian gia hạn có hợp lệ không
                if (thangGiaHan > 0) {
                    break; // Thoát khỏi vòng lặp nếu thời gian gia hạn hợp lệ
                } else {
                    System.out.println("Thời gian gia hạn phải là số dương!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ cho thời gian gia hạn!");
            }
        }

        // Gia hạn hợp đồng
        hopDong.giaHan(thangGiaHan);
        HopDongService.giaHanHopDong(hopDong);

        System.out.println("Gia hạn hợp đồng thành công!");
    }

    public static void xemChiTietHopDong() {
        Scanner sc = new Scanner(System.in);
        int maHopDong = -1;

        // Nhập mã hợp đồng và xử lý lỗi người dùng
        while (true) {
            try {
                System.out.println("Nhập mã hợp đồng để xem chi tiết: ");
                maHopDong = Integer.parseInt(sc.nextLine());
                break; // Thoát vòng lặp nếu nhập mã hợp lệ
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ cho mã hợp đồng!");
            }
        }

        // Tìm kiếm hợp đồng dựa trên mã
        HopDong hopDong = HopDongService.getHopDongById(maHopDong);
        if (hopDong == null) {
            System.out.println("Không tìm thấy hợp đồng với mã: " + maHopDong);
            return;
        }

        // Lấy thông tin phòng và người thuê liên quan
        Phong phong = PhongService.getPhongById(hopDong.getMaPhong());
        NguoiThue nguoiThue = NguoiThueService.getNguoiThueById(hopDong.getMaNguoiThue());

        // Kiểm tra phòng và người thuê có tồn tại không
        if (phong == null || nguoiThue == null) {
            System.out.println("Không thể lấy thông tin phòng hoặc người thuê.");
            return;
        }

        // Hiển thị tiêu đề thông tin hợp đồng
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("|                   THÔNG TIN HỢP ĐỒNG                      |");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("Mã hợp đồng: " + hopDong.getMaHopDong());
        System.out.println("Ngày bắt đầu: " + hopDong.getNgayBatDau());
        System.out.println("Ngày kết thúc: " + hopDong.getNgayKetThuc());
        System.out.println("Giá thuê hợp đồng: " + hopDong.getGiaThueHopDong());
        System.out.println("Tiền cọc: " + hopDong.getTienCoc());

        // Hiển thị thông tin phòng
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("|                      THÔNG TIN PHÒNG                      |");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("Mã phòng: " + phong.getMaPhong());
        System.out.println("Diện tích: " + phong.getDienTich());
        System.out.println("Mô tả: " + phong.getMoTa());

        // Hiển thị thông tin người thuê
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("|                   THÔNG TIN NGƯỜI THUÊ                    |");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("Mã người thuê: " + nguoiThue.getMaNguoiThue());
        System.out.println("Họ tên: " + nguoiThue.getTen());
        System.out.println("Ngày sinh: " + nguoiThue.getNgaySinh());
        System.out.println("Giới tính: " + nguoiThue.getGioiTinh());
        System.out.println("Địa chỉ: " + nguoiThue.getDiaChi());
        System.out.println("Số điện thoại: " + nguoiThue.getSoDienThoai());

        System.out.println("+-----------------------------------------------------------+");
    }

    public static float getGiaThue(int maHopDong) {
        return listHopDong.getHopDong(maHopDong).data.getGiaThueHopDong();
    }

}
