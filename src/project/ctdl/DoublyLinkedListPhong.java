package project.ctdl;

import project.quanlykhutro.controller.PhongController;
import project.quanlykhutro.models.Phong;
import project.quanlykhutro.services.PhongService;

import java.util.List;

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
        boolean found = false; // Biến để theo dõi xem phòng có được tìm thấy hay không

        while (current != null) {
            if (current.data.getMaPhong() == maPhong) { // So sánh mã phòng
                System.out.printf("| %-8d | %-20.2f | %-8.2f | %-8s | %-35s | %-8d |\n", current.data.getMaPhong(),
                        current.data.getDienTich(), current.data.getGiaThue(), current.data.getTrangThai(),
                        current.data.getMoTa(), current.data.getMaQuanLy());
                found = true; // Đánh dấu là đã tìm thấy phòng
                break; // Thoát vòng lặp khi tìm thấy phòng
            }
            current = current.next; // Di chuyển đến nút tiếp theo trong danh sách
        }

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
    }

    public void deletePhong(int maPhong) {
        NodePhong current = searchPhong(maPhong);

        // Nếu không tìm thấy phòng với mã tương ứng
        if (current == null) {
            System.out.println("Không tìm thấy phòng có mã: " + maPhong);
            return;
        }

        // Trường hợp xóa phần tử đầu tiên
        if (current == first) {
            // Nếu chỉ có một phần tử trong danh sách
            if (first == last) {
                first = null;
                last = null;
            } else {
                first = first.next;
                first.prev = null;
            }
        }
        // Trường hợp xóa phần tử cuối cùng
        else if (current == last) {
            last = last.prev;
            last.next = null;
        }
        // Trường hợp xóa phần tử ở giữa
        else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        size--;
        System.out.println("Xóa phòng có mã " + maPhong + " thành công.");
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

        System.out.println("+----------+----------------------+----------+----------+----------+");
        System.out.println("Tổng số phòng trống: " + count);
    }



}
