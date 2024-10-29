package project.ctdl;

import project.quanlykhutro.controller.PhongController;
import project.quanlykhutro.models.Phong;
import project.quanlykhutro.services.PhongService;

import java.util.List;
import java.util.Scanner;

public class DoublyLinkedListPhong {
    private NodePhong first, last;
    private int size;
    private PhongService phongService;

    public DoublyLinkedListPhong() {
        this.first = null;
        this.last = null;
        this.size = 0;
        this.phongService = new PhongService();
        loadDataFromDatabase();
    }

    public int getSize() {
        return size;
    }


    // Tải dữ liệu từ MySQL vào danh sách liên kết đôi
    public void loadDataFromDatabase() {
        PhongService.getAllPhong(this);
    }


    public void addLast(Phong phong) {
        NodePhong newNode = new NodePhong(phong);
        if (first == null && last == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        size++;
        System.out.println("Them phong thanh cong!");
    }

    public NodePhong searchPhong(int maPhong) {
        NodePhong current = first;
        boolean found = false;
        PhongController.hienThiTieuDePhong();
        while (current != null) {
            if (current.data.getMaPhong() == maPhong) {
                System.out.printf("| %-8d | %-20.2f | %-8.2f | %-8s | %-35s | %-8d |\n", current.data.getMaPhong(),
                        current.data.getDienTich(), current.data.getGiaThue(), current.data.getTrangThai(),
                        current.data.getMoTa(), current.data.getMaQuanLy());
                found = true;
                break;
            }
            current = current.next;
        }
        System.out.println("+----------+----------------------+----------+----------+------------------------------------+----------+");


        if (!found) {
            System.out.println("Không tìm thấy phòng có mã: " + maPhong);
            return null;
        }

        return current;
    }

    public void printListPhong() {
        NodePhong current = first;
        while (current != null) {
            System.out.printf("| %-8d | %-20.2f | %-8.2f | %-8s | %-35s | %-8d |\n", current.data.getMaPhong(),
                    current.data.getDienTich(), current.data.getGiaThue(), current.data.getTrangThai(),
                    current.data.getMoTa(), current.data.getMaQuanLy());

            current = current.next;
        }
        System.out.println("+----------+----------------------+----------+----------+------------------------------------+----------+");

    }

    //    public void deletePhong(int maPhong) {
//        NodePhong current = searchPhong(maPhong);
//
//        // Nếu không tìm thấy phòng với mã tương ứng
//        if (current == null) {
//            System.out.println("Không tìm thấy phòng có mã: " + maPhong);
//            return;
//        }
//
//        // Trường hợp xóa phần tử đầu tiên
//        if (current == first) {
//            // Nếu chỉ có một phần tử trong danh sách
//            if (first == last) {
//                first = null;
//                last = null;
//            } else {
//                first = first.next;
//                first.prev = null;
//            }
//        }
//        // Trường hợp xóa phần tử cuối cùng
//        else if (current == last) {
//            last = last.prev;
//            last.next = null;
//        }
//        // Trường hợp xóa phần tử ở giữa
//        else {
//            current.prev.next = current.next;
//            current.next.prev = current.prev;
//        }
//
//        size--;
//        System.out.println("Xóa phòng có mã " + maPhong + " thành công.");
//    }
//
    public boolean checkPhongCoNguoi(int maPhong) {
        NodePhong current = first;

        while (current != null) {
            // Kiểm tra nếu phòng có mã khớp và trạng thái là "Có Người"
            if (current.data.getMaPhong() == maPhong) {
                if (current.data.getTrangThai().equalsIgnoreCase("Có Người")) {
                    return true; // Nếu có người ở, trả về true ngay lập tức
                } else {
                    return false; // Nếu không có người ở, trả về false
                }
            }
            current = current.next;
        }

        // Nếu không tìm thấy phòng
        System.out.println("Không tìm thấy phòng có mã: " + maPhong);
        return false;
    }


    public void updateTrangThai(int maPhong) {
        Scanner sc = new Scanner(System.in);
        NodePhong current = searchPhong(maPhong);

        // Kiểm tra xem phòng có tồn tại không
        if (current == null) {
            System.out.println("Không tìm thấy phòng có mã: " + maPhong);
            return;
        }

        // Kiểm tra xem phòng có người ở không
        if (checkPhongCoNguoi(maPhong)) {
            System.out.println("Phòng này đang có người ở không thể cập nhật trạng thái!");
            return;
        }

        String trangThai = "";
        boolean validChoice = false; // Biến để kiểm tra lựa chọn hợp lệ

        // Nhập trạng thái phòng
        while (!validChoice) {
            System.out.println("Nhập trạng thái: (1 - Phòng trống, 2 - Bị Hư)");
            int chon;

            // Kiểm tra nhập liệu có phải là số hay không
            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Nhập sai! Vui lòng nhập lại!");
                continue; // Trở về đầu vòng lặp
            }

            switch (chon) {
                case 1:
                    trangThai = "Trống";
                    validChoice = true;
                    break;
                case 2:
                    trangThai = "Bị Hư";
                    validChoice = true;
                    break;
                default:
                    System.out.println("Nhập sai! Vui lòng nhập lại!");
            }
        }

        // Cập nhật trạng thái phòng
        current.data.setTrangThai(trangThai);
        PhongService.updateTrangThai(maPhong, trangThai);
        System.out.println("Cập nhật phòng có mã " + maPhong + " thành công.");
    }



    public void updatePhong(int maPhong, Phong phongMoi) {
        NodePhong current = searchPhong(maPhong);

        if (current == null) {
            System.out.println("Không tìm thấy phòng có mã: " + maPhong);
            return;
        }

        // Cập nhật thông tin của phòng hiện tại với dữ liệu mới
        current.data.setDienTich(phongMoi.getDienTich());
        current.data.setGiaThue(phongMoi.getGiaThue());
        current.data.setTrangThai(phongMoi.getTrangThai());
        current.data.setMoTa(phongMoi.getMoTa());
        current.data.setMaQuanLy(phongMoi.getMaQuanLy());

        System.out.println("Cập nhật phòng có mã " + maPhong + " thành công.");
    }

    public void thongKePhongTrong() {
        NodePhong current = first;
        int count = 0;  // Biến đếm số lượng phòng trống

        PhongController.hienThiTieuDePhong();

        // Duyệt qua danh sách liên kết đôi
        while (current != null) {
            // Kiểm tra nếu phòng đang trống
            if (current.data.getTrangThai().equalsIgnoreCase("Trống")) {
                System.out.printf("| %-8d | %-20.2f | %-8.2f | %-8s | %-35s | %-8d |\n", current.data.getMaPhong(),
                        current.data.getDienTich(), current.data.getGiaThue(), current.data.getTrangThai(),
                        current.data.getMoTa(), current.data.getMaQuanLy());
                count++;
            }
            current = current.next;
        }

        System.out.println("+----------+----------------------+----------+----------+------------------------------------+----------+");
        System.out.println("Tổng số phòng trống: " + count);
    }

    public void timKiemPhongTheoTrangThai(String trangThai) {
        NodePhong current = first;
        int count = 0;  // Biến đếm số lượng phòng trống

        PhongController.hienThiTieuDePhong();

        // Duyệt qua danh sách liên kết đôi
        while (current != null) {
            // Kiểm tra nếu phòng đang trống
            if (current.data.getTrangThai().equalsIgnoreCase(trangThai)) {
                System.out.printf("| %-8d | %-20.2f | %-8.2f | %-8s | %-35s | %-8d |\n", current.data.getMaPhong(),
                        current.data.getDienTich(), current.data.getGiaThue(), current.data.getTrangThai(),
                        current.data.getMoTa(), current.data.getMaQuanLy());
                count++;
            }
            current = current.next;
        }

        System.out.println("+----------+----------------------+----------+----------+------------------------------------+----------+");
        System.out.println("Tổng số phòng " + trangThai + ": " + count);
    }

    public void updateStatusWithHopDong(int maPhong) {
        NodePhong current = searchPhong(maPhong);
        String trangThai = "Có Người";

        // Cập nhật trạng thái phòng
        current.data.setTrangThai(trangThai);
        PhongService.updateTrangThai(maPhong, trangThai);
        System.out.println("Cập nhật phòng có mã " + maPhong + " thành công.");
    }

    public boolean checkPhongTrong() {
        NodePhong current = first;
        int count = 0;

        // Duyệt qua danh sách liên kết đôi
        while (current != null) {
            // Kiểm tra nếu phòng đang trống
            if (current.data.getTrangThai().equalsIgnoreCase("Trống")) {
                count++;

            }
            current = current.next;
        }
        System.out.println("Tổng số phòng trống: " + count);
        return count > 0;

    }

}
