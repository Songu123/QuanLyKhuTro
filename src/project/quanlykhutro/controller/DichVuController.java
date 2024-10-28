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
            int chon = sc.nextInt();
            sc.nextLine();
            switch (chon) {
                case 1:
                    addDichVu();
                    break;
                case 2:
                    updateDichVu();
                    break;
                case 3:
                    updateStatus();
                    break;
                case 4:
                    searchDichVu();
                    break;
                case 5:
                    printListDichVu();
                    break;
                case 6:
                    sortWithPrice();
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

        String trangThai = "Hoạt Động";

        System.out.println("Đã nhập thành công thông tin người thuê!");
        dichVu = new DichVu(ten, donGia, trangThai);
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
        System.out.println("+------------+----------------------+------------+------------+");
        System.out.printf("| %-10s | %-20s | %-10s | %-10s |\n",
                "MaDichVu", "TenDichVu", "DonGia", "TrangThai"); // Thêm trường trạng thái
        System.out.println("+------------+----------------------+------------+------------+");
    }

    public static void searchDichVu() {
        int id = 0;
        try {
            System.out.println("Nhập ID phòng tìm kiếm: ");
            id = Integer.parseInt(sc.nextLine());
            hienThiTieuDeDichVu();
//            listDichVu.searchDichVu(id);
            listDichVu.hienThiDichVu(listDichVu.searchDichVuDeQuy(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateStatus() {
        try {
            System.out.print("Nhập ID Phòng muốn cập nhật trạng thái: ");
            int id = Integer.parseInt(sc.nextLine().trim()); // Loại bỏ khoảng trắng thừa

            if (listDichVu.searchDichVuDeQuy(id) != null) { // Giả sử bạn có một phương thức để kiểm tra ID
                System.out.println("ID phòng không tồn tại. Vui lòng kiểm tra lại.");
                return;
            }

            System.out.print("Nhập trạng thái (1 - Hoạt Động, 2 - Không Hoạt Động): ");
            int chon = Integer.parseInt(sc.nextLine().trim()); // Loại bỏ khoảng trắng thừa
            String trangThai;

            switch (chon) {
                case 1:
                    trangThai = "Hoạt Động";
                    break;
                case 2:
                    trangThai = "Không Hoạt Động";
                    break;
                default:
                    System.out.println("Nhập sai! Nhập (1, 2)!");
                    return;
            }

            // Cập nhật trạng thái trong danh sách dịch vụ
            listDichVu.updateStatus(id, trangThai);
            // Cập nhật trạng thái dịch vụ
            DichVuService.updateStatus(id, trangThai);
            System.out.println("Cập nhật trạng thái thành công cho ID: " + id);

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập một số hợp lệ cho ID và trạng thái.");
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    public static void updateDichVu() {
        try {
            System.out.print("Nhập ID Dịch Vụ muốn cập nhật: ");
            int id = Integer.parseInt(sc.nextLine().trim()); // Loại bỏ khoảng trắng thừa

            // Kiểm tra xem ID có tồn tại hay không
            if (listDichVu.searchDichVuDeQuy(id) == null) { // Kiểm tra nếu ID không tồn tại
                System.out.println("ID dịch vụ không tồn tại. Vui lòng kiểm tra lại.");
                return;
            }

            // Nhập thông tin dịch vụ mới
            DichVu dichVu = nhapThongTin();

            // Cập nhật dịch vụ trong danh sách
            listDichVu.updateDichVu(id, dichVu);
            // Cập nhật dịch vụ trong dịch vụ
            DichVuService.updateDichVu(dichVu, id);

            System.out.println("Cập nhật dịch vụ thành công cho ID: " + id);

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập một số hợp lệ cho ID.");
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
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
        NodeDichVu current =  listDichVu.searchDichVuDeQuy(maDichVu);
        return current.data.getTenDichVu();
    }

//    Sử dụng thuật toán sắp xếp nổi bọt để sắp xếp
    public static void sortWithPrice() {
        listDichVu.bubbleSort();
    }

}
