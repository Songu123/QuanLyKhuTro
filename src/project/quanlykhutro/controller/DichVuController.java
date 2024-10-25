package project.quanlykhutro.controller;

import project.ctdl.DoublyLinkedListDichVu;
import project.ctdl.DoublyLinkedListDichVu;
import project.ctdl.NodeDichVu;
import project.quanlykhutro.models.DichVu;
import project.quanlykhutro.services.DichVuService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DichVuController {
    public static Scanner sc = new Scanner(System.in);
    public static DoublyLinkedListDichVu listDichVu = new DoublyLinkedListDichVu();

    public static void run() {
        while (true) {
            Menu.menuQuanLyDichVu();
            System.out.println("Vui lòng chọn chức năng (0-6): ");
            int chon = Integer.parseInt(sc.nextLine());
            switch (chon) {
                case 1:
                    addDichVu();
                    break;
                case 2:
                    updateDichVu();
                    break;
                case 3:
                    deleteDichVu();
                    break;
                case 4:
                    searchDichVu();
                    break;
                case 5:
                    printListDichVu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Nhập sai! Vui lòng nhập lại (0-6)!");
            }
        }
    }

    public static DichVu nhapThongTin() {
        DichVu dichVu;
        String ten;

        // Nhập tên dịch vụ
        while (true) {
            try {
                System.out.println("Nhập tên dịch vụ: ");
                ten = sc.nextLine();

                if (!ten.isEmpty()) {
                    break;
                } else {
                    System.out.println("Bạn chưa tên dịch vụ! Vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // Nhập đơn giá
        float donGia;
        while (true) {
            try {
                System.out.println("Nhập đơn giá: ");
                donGia = Float.parseFloat(sc.nextLine());

                if (donGia > 0) {  // Đảm bảo đơn giá là số dương
                    break;
                } else {
                    System.out.println("Đơn giá không hợp lệ! Vui lòng nhập số dương.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ cho đơn giá!");
            }
        }

        System.out.println("Đã nhập thành công thông tin người thuê!");
        dichVu = new DichVu(ten, donGia);
        System.out.println(dichVu);
        return dichVu;
    }


    public static void addDichVu() {
        try {
            DichVu dichVu = nhapThongTin();
            DichVuService.addDichVu(dichVu);
            listDichVu.addLast(DichVuService.getLastRow());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public void hienThiThongTin() {
//        System.out.printf("| %-8s | %-20s | %-8s | %-8s | %-8s |\n", getMaDichVu(), getDienTich(), getGiaThue(), getTrangThai(), getMoTa(), getMaQuanLy());
//    }

    // Hàm hiển thị tiêu đề dịch vụ
    public static void hienThiTieuDeDichVu() {
        System.out.println("+------------+----------------------+------------+");
        System.out.printf("| %-10s | %-20s | %-10s |\n",
                "MaDichVu", "TenDichVu", "DonGia");
        System.out.println("+------------+----------------------+------------+");
    }

    public static void searchDichVu() {
        int id = 0;
        try {
            System.out.println("Nhập ID phòng tìm kiếm: ");
            id = Integer.parseInt(sc.nextLine());
            hienThiTieuDeDichVu();
            listDichVu.searchDichVu(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteDichVu() {
        try {
            System.out.println("Nhập ID Phòng muốn xoá: ");
            int id = Integer.parseInt(sc.nextLine());

            listDichVu.deleteDichVu(id);
            DichVuService.deleteDichVu(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void updateDichVu() {
        try {
            System.out.println("Nhập ID Phòng muốn cập nhật: ");
            int id = Integer.parseInt(sc.nextLine());

            DichVu dichVu = nhapThongTin();
            listDichVu.updateDichVu(id, dichVu);
            DichVuService.updateDichVu(dichVu, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void printListDichVu() {
        try {
            if (listDichVu.getSize() > 0) {
                hienThiTieuDeDichVu();
                listDichVu.printListDichVu();
            } else {
                System.out.println("Danh sách phòng rỗng!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getNameDichVu(int maDichVu) {
        NodeDichVu current =  listDichVu.searchDichVu(maDichVu);
        return current.data.getTenDichVu();
    }

}
