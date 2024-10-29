package project.quanlykhutro.controller;

import project.ctdl.DoublyLinkedListPhong;
import project.ctdl.NodePhong;
import project.quanlykhutro.models.DichVu;
import project.quanlykhutro.models.Phong;
import project.quanlykhutro.services.DichVuService;
import project.quanlykhutro.services.PhongService;

import javax.xml.soap.Node;
import java.util.Scanner;

public class PhongController {
    public static Scanner sc = new Scanner(System.in);
    public static DoublyLinkedListPhong listPhong = new DoublyLinkedListPhong();

    public static void run() {
        while (true) {
            Menu.menuQuanLyPhong();
            System.out.println("Vui lòng chọn chức năng (0-6): ");
            int chon = Integer.parseInt(sc.nextLine());
            switch (chon) {
                case 1:
                    addPhong();
                    break;
                case 2:
                    updatePhong();
                    break;
                case 3:
                    updateStatus();
                    break;
                case 4:
                    listPhong.thongKePhongTrong();
                    break;
                case 5:
                    printPhongWithStatus();
                    break;
                case 6:
                    searchPhong();
                    break;
                case 7:
                    printListPhong();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Nhập sai! Vui lòng nhập lại (0-6)!");
            }
        }
    }

    public static Phong nhapThongTin() {
        System.out.println("hello các bạn");
        Phong phong;
        float dienTich;

        // Nhập diện tích
        while (true) {
            try {
                System.out.println("Nhập diện tích phòng: ");
                dienTich = Float.parseFloat(sc.nextLine());

                if (dienTich > 0) {
                    break;
                } else {
                    System.out.println("Diện tích phòng không hợp lệ! Vui lòng nhập số dương!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ cho diện tích!");
            }
        }

        // Nhập giá thuê
        float giaThue;
        while (true) {
            try {
                System.out.println("Nhập giá thuê: ");
                giaThue = Float.parseFloat(sc.nextLine());

                if (giaThue > 0) {
                    break;
                } else {
                    System.out.println("Giá thuê không hợp lệ! Vui lòng nhập số dương!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ cho giá thuê!");
            }
        }

        // Nhập trạng thái phòng
        String trangThai = "Trống";


        // Nhập mô tả
        String moTa = "";
        while (true) {
            System.out.println("Nhập mô tả (tối đa 225 ký tự): ");
            moTa = sc.nextLine().trim();

            if (moTa.length() > 225) {
                System.out.println("Mô tả quá dài! Vui lòng nhập ít hơn 225 ký tự.");
            } else {
                break;
            }
        }

        // Nhập mã quản lý
        int maQuanLy = 0;
        while (true) {
            try {
                System.out.println("Nhập mã quản lý: ");
                maQuanLy = Integer.parseInt(sc.nextLine());

                // Kiểm tra xem mã quản lý có tồn tại không
                DichVu dichVu = DichVuService.getDichVuById(maQuanLy);
                if (dichVu != null && dichVu.getMaDichVu() == maQuanLy) {
                    break;
                } else {
                    System.out.println("Mã quản lý không hợp lệ! Vui lòng nhập mã Quản lý đã tạo trước đó.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ cho mã quản lý!");
            }
        }

        System.out.println("Đã nhập thành công thông tin phòng:");
        phong = new Phong(dienTich, giaThue, trangThai, moTa, maQuanLy);
        System.out.println(phong);
        return phong;
    }


    public static void addPhong() {
        try {
            Phong phong = nhapThongTin();
            PhongService.addPhong(phong);
            listPhong.addLast(PhongService.getLastRow());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public void hienThiThongTin() {
//        System.out.printf("| %-8s | %-20s | %-8s | %-8s | %-8s |\n", getMaPhong(), getDienTich(), getGiaThue(), getTrangThai(), getMoTa(), getMaQuanLy());
//    }

    public static void hienThiTieuDePhong() {
        System.out.println("+----------+----------------------+----------+----------+------------------------------------+----------+");
        System.out.printf("| %-8s | %-20s | %-8s | %-8s | %-35s | %-8s |\n",
                "MaPhong", "DienTich", "GiaThue", "TrangThai", "MoTa", "MaQuanLy");
        System.out.println("+----------+----------------------+----------+----------+------------------------------------+----------+");
    }

    public static void searchPhong() {
        int id = 0;
        try {
            System.out.println("Nhập ID phòng tìm kiếm: ");
            id = Integer.parseInt(sc.nextLine());
            hienThiTieuDePhong();
            listPhong.searchPhong(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateStatus() {
        try {
            System.out.println("Nhập ID Phòng muốn cập nhật trạng thái: ");
            int id = Integer.parseInt(sc.nextLine());

            listPhong.updateTrangThai(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void updatePhong() {
        try {
            System.out.println("Nhập ID Phòng muốn cập nhật: ");
            int id = Integer.parseInt(sc.nextLine());

            // Kiểm tra xem phòng có người ở không
            if (listPhong.checkPhongCoNguoi(id)) {
                System.out.println("Phòng này đang có người ở, không thể cập nhật thông tin phòng!");
                return;
            }

            if (listPhong.searchPhong(id) == null){
//                System.out.println("Không tìm thấy mã phòng tồn tại!");
                return;
            }

            // Nhập thông tin mới cho phòng
            Phong phong = nhapThongTin();

            // Thực hiện cập nhật thông tin phòng
            listPhong.updatePhong(id, phong);
            PhongService.updatePhong(phong, id);
            System.out.println("Cập nhật thông tin phòng thành công.");

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập một số hợp lệ cho ID phòng.");
        } catch (Exception e) {
            System.out.println("Lỗi không xác định: " + e.getMessage());
        }
    }


    public static void printListPhong() {
        try {
            if (listPhong.getSize() > 0) {
                hienThiTieuDePhong();
                listPhong.printListPhong();
            } else {
                System.out.println("Danh sách phòng rỗng!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printPhongWithStatus() {
        String trangThai = "";
        while (true) {
            System.out.println("Nhập trạng thái: (1 - Phòng trống, 2 - Có người, 3 - Bị Hư)");
            int chon = sc.nextInt();
            sc.nextLine();
            switch (chon) {
                case 1:
                    trangThai = "Trống";
                    break;
                case 2:
                    trangThai = "Có Người";
                    break;
                case 3:
                    trangThai = "Bị Hư";
                    break;
                default:
                    System.out.println("Nhâp sai! Vui lòng nhập lại!");
            }
            listPhong.timKiemPhongTheoTrangThai(trangThai);
            break;
        }
    }

    public static Phong getPhongWithId(int maPhong) {
        NodePhong newNode = listPhong.searchPhong(maPhong);
        Phong phong = new Phong(newNode.data.getDienTich(), newNode.data.getGiaThue(), newNode.data.getTrangThai(), newNode.data.getMoTa(), newNode.data.getMaQuanLy());
        return phong;
    }

    public static void updatePhongWithHopDong(int maPhong) {
        listPhong.updateStatusWithHopDong(maPhong);
    }

    public static boolean checkPhongTrong() {
        return listPhong.checkPhongTrong();
    }
}
