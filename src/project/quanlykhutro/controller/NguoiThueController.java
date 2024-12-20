package project.quanlykhutro.controller;

import project.ctdl.DoublyLinkedListNguoiThue;
import project.quanlykhutro.models.DichVu;
import project.quanlykhutro.models.NguoiThue;
import project.quanlykhutro.services.DichVuService;
import project.quanlykhutro.services.NguoiThueService;
import sun.util.resources.LocaleData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;


public class NguoiThueController {
    public static Scanner sc = new Scanner(System.in);
    public static DoublyLinkedListNguoiThue listNguoiThue = new DoublyLinkedListNguoiThue();

    public static void run() {
        while (true) {
            Menu.menuQuanLyNguoiThue();
            System.out.println("Vui lòng chọn chức năng (0-6): ");
            try {
                int chon = Integer.parseInt(sc.nextLine());
                switch (chon) {
                    case 1:
                        addNguoiThue();
                        break;
                    case 2:
                        updateNguoiThue();
                        break;
                    case 3:
                        deleteNguoiThue();
                        break;
                    case 4:
                        updateStatus();
                        break;
                    case 5:
                        searchNguoiThue();
                        break;
                    case 6:
                        printListNguoiThue();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Nhập sai! Vui lòng nhập lại (0-6)!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ.");
            }
        }
    }

    public static NguoiThue nhapThongTin(String tenHam) {
        NguoiThue nguoiThue = null;
        String ten;

        // Nhập diện tích
        while (true) {
            try {
                System.out.println("Nhập họ tên người thuê: ");
                ten = sc.nextLine();

                if (!ten.isEmpty()) {
                    break;
                } else {
                    System.out.println("Bạn chưa nhập họ tên! Vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
//        Nhập ngày sinh
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ngaySinh;

        while (true) {
            try {
                System.out.println("Nhập ngày sinh (định dạng: yyyy-MM-dd): ");
                String input = sc.nextLine();

                ngaySinh = LocalDate.parse(input, formatter);

                if (ngaySinh.isAfter(LocalDate.now())) {
                    System.out.println("Lỗi: Ngày sinh không được là một ngày trong tương lai!");
                } else {
                    break;
                }

            } catch (DateTimeParseException e) {
                System.out.println("Lỗi: Vui lòng nhập ngày theo định dạng yyyy-MM-dd!");
            } catch (Exception e) {
                // Bắt mọi lỗi khác có thể xảy ra
                System.out.println("Lỗi không xác định: " + e.getMessage());
            }
        }

        // Nhập giới tính
        String gioiTinh = "";
        while (true) {
            System.out.println("Nhập giới tính (Nhấn 1 'Nam', 2 'Nữ'): ");
            int chon;
            try {
                chon = Integer.parseInt(sc.nextLine());

                if (chon == 1) {
                    gioiTinh = "Nam";
                    break;
                } else if (chon == 2) {
                    gioiTinh = "Nữ";
                    break;
                } else {
                    System.out.println("Nhập sai! Vui lòng nhập lại (1 hoặc 2)!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số (1 hoặc 2)!");
            }
        }

        // Nhập địa chỉ
        String diaChi = "";
        while (true) {
            System.out.println("Nhập địa chỉ (tối đa 225 ký tự): ");
            diaChi = sc.nextLine().trim();

            if (diaChi.length() > 225) {
                System.out.println("Mô tả quá dài! Vui lòng nhập ít hơn 225 ký tự.");
            } else {
                break;
            }
        }

        // Nhập số điện thoại
        String soDienThoai = "";
        while (true) {
            System.out.println("Nhập số điện thoại (chỉ chứa số, độ dài 10-11 ký tự): ");
            soDienThoai = sc.nextLine().trim();

            // Kiểm tra nếu số điện thoại không phải là chuỗi rỗng và chỉ chứa ký tự số
            if (!soDienThoai.isEmpty() && soDienThoai.matches("\\d+")) {
                if (soDienThoai.length() >= 10 && soDienThoai.length() <= 11) {
                    System.out.println("Số điện thoại hợp lệ: " + soDienThoai);
                    break;
                } else {
                    System.out.println("Lỗi: Số điện thoại phải có độ dài từ 10 đến 11 ký tự.");
                }
            } else {
                System.out.println("Lỗi: Vui lòng chỉ nhập các ký tự số!");
            }
        }

        String trangThai = " ";
        trangThai = "Đang Chờ";

        if ("Create".equals(tenHam)) {
            nguoiThue = new NguoiThue(ten, ngaySinh, gioiTinh, diaChi, soDienThoai, trangThai);
        } else if ("Update".equals(tenHam)) {
            nguoiThue = new NguoiThue(ten, ngaySinh, gioiTinh, diaChi, soDienThoai);
        }

        System.out.println("Đã nhập thành công thông tin người thuê!");
        return nguoiThue;
    }


    public static void addNguoiThue() {
        try {
            NguoiThue nguoiThue = nhapThongTin("Create");
            NguoiThueService.addNguoiThue(nguoiThue);
            listNguoiThue.addLast(NguoiThueService.getLastRow());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public void hienThiThongTin() {
//        System.out.printf("| %-8s | %-20s | %-8s | %-8s | %-8s |\n", getMaNguoiThue(), getDienTich(), getGiaThue(), getTrangThai(), getMoTa(), getMaQuanLy());
//    }

    public static void hienThiTieuDeNguoiThue() {
        System.out.println("+------------+----------------------+------------+----------+------------------------------------+--------------+--------------+");
        System.out.printf("| %-10s | %-20s | %-10s | %-8s | %-35s | %-12s | %-12s |\n",
                "MaNguoiThue", "HoTen", "NgaySinh", "GioiTinh", "DiaChi", "SoDienThoai", "TrangThai");
        System.out.println("+------------+----------------------+------------+----------+------------------------------------+--------------+--------------+");
    }

    public static void searchNguoiThue() {
        int id = 0;
        try {
            System.out.println("Nhập ID phòng tìm kiếm: ");
            id = Integer.parseInt(sc.nextLine());
            hienThiTieuDeNguoiThue();
            listNguoiThue.searchNguoiThue(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteNguoiThue() {
        try {
            System.out.println("Nhập ID Người thuê muốn xoá: ");
            int id = Integer.parseInt(sc.nextLine());

            listNguoiThue.deleteNguoiThue(id);
            NguoiThueService.deleteNguoiThue(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void updateNguoiThue() {
        try {
            System.out.println("Nhập ID Người Thuê muốn cập nhật: ");
            int id = Integer.parseInt(sc.nextLine());
            if (listNguoiThue.searchNguoiThue(id) == null) {
                System.out.println("Mà người thuê không tồn tại! Vui lòng nhập lại ID!");
                return;
            }
            NguoiThue nguoiThue = nhapThongTin("Update");
            listNguoiThue.updateNguoiThue(id, nguoiThue);
            NguoiThueService.updateNguoiThue(nguoiThue, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateStatus() {
        int id = 0;
        try {
            System.out.println("Nhập ID Người Thuê muốn cập nhật: ");
            id = Integer.parseInt(sc.nextLine());
            if (listNguoiThue.searchNguoiThue(id) == null) {
                System.out.println("Mã người thuê không tồn tại! Vui nhập lại ID!");
                return;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String trangThai = "";
        while (true) {
            System.out.println("Nhập trạng thái: (1 - Đang Thuê, 2 - Đã Trả Phòng, 3 - Đang Chờ)");
            int chon = sc.nextInt();
            sc.nextLine();
            switch (chon) {
                case 1:
                    trangThai = "Đang Thuê";
                    break;
                case 2:
                    trangThai = "Đã Trả Phòng";
                    break;
                case 3:
                    trangThai = "Đang Chờ";
                    break;
                default:
                    System.out.println("Nhâp sai! Vui lòng nhập lại!");
            }
            listNguoiThue.updateTrangThaiNguoiThue(id, trangThai);
            break;
        }
    }

    public static void printListNguoiThue() {
        try {
            if (listNguoiThue.getSize() > 0) {
                hienThiTieuDeNguoiThue();
                listNguoiThue.printListNguoiThue();
            } else {
                System.out.println("Danh sách phòng rỗng!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean checkNguoiThue(int maNguoiThue) {
        return listNguoiThue.checkNguoiThue(maNguoiThue);
    }


}
