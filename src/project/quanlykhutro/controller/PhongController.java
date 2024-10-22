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
                    deletePhong();
                    break;
                case 4:
                    listPhong.thongKePhongTrong();
                    break;
                case 5:
                    searchPhong();
                    break;
                case 6:
                    printListPhong();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Nhập sai! Vui lòng nhập lại (0-6)!");
            }
        }
    }

    public static Phong nhapThongTin() {
        System.out.println("hello các bạn");
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
        String trangThai = "";
        while (true) {
            System.out.println("Nhập trạng thái (Nhấn 1 'Phòng trống', 2 'Phòng có người'): ");
            int chon;
            try {
                chon = Integer.parseInt(sc.nextLine());

                if (chon == 1) {
                    trangThai = "Trong";
                    break;
                } else if (chon == 2) {
                    trangThai = "CoNguoi";
                    break;
                } else {
                    System.out.println("Nhập sai! Vui lòng nhập lại (1 hoặc 2)!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số (1 hoặc 2)!");
            }
        }

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

        System.out.println("Xin chào" + dienTich + " " +  giaThue + " " +  trangThai + " " +  moTa + " " +  maQuanLy);

        // Tạo đối tượng Phong và trả về
        Phong phong = new Phong(dienTich, giaThue, trangThai, moTa, maQuanLy);
        System.out.println("Đã nhập thành công thông tin phòng:");
        System.out.println(phong);
        return phong;
    }


    public static void addPhong() {
        try {
            Phong phong = nhapThongTin();
            PhongService.addPhong(phong, listPhong);
        }catch (Exception e) {
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
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deletePhong() {
        try {
            System.out.println("Nhập ID Phòng muốn xoá: ");
            int id = Integer.parseInt(sc.nextLine());

            listPhong.deletePhong(id);
            PhongService.deletePhong(id);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void updatePhong() {
        try {
            System.out.println("Nhập ID Phòng muốn cập nhật: ");
            int id = Integer.parseInt(sc.nextLine());

            Phong phong = nhapThongTin();
            listPhong.updatePhong(id, phong);
            PhongService.updatePhong(phong, id);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void printListPhong() {
        try{
            if (listPhong.getSize() > 0){
                hienThiTieuDePhong();
                listPhong.printListPhong();
            }else {
                System.out.println("Danh sách phòng rỗng!");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
