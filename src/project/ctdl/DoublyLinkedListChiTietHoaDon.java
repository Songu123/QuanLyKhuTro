package project.ctdl;

import project.quanlykhutro.models.ChiTietHoaDon;
import project.quanlykhutro.models.HoaDon;
import project.quanlykhutro.services.ChiTietHoaDonService;

public class DoublyLinkedListChiTietHoaDon {
    private NodeChiTietHoaDon first, last;
    private int size;

    // Constructor không nhận tham số và khởi tạo giá trị mặc định
    public DoublyLinkedListChiTietHoaDon() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    // Lấy kích thước danh sách
    public int getSize() {
        return size;
    }

    // Thêm hóa đơn chi tiết vào danh sách
    public void addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        NodeChiTietHoaDon newNode = new NodeChiTietHoaDon(chiTietHoaDon);
        if (first == null && last == null) { // Danh sách rỗng
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        size++;
        ChiTietHoaDonService.addChiTietHoaDon(chiTietHoaDon);  // Thêm vào cơ sở dữ liệu
        System.out.println("Tạo hoá đơn thành công!");
    }

    // Tìm kiếm hóa đơn theo mã
    public NodeChiTietHoaDon searchChiTietHoaDon(int maHoaDon) {
        NodeChiTietHoaDon current = first;
        while (current != null) {
            if (current.data.getMaHoaDon() == maHoaDon) {
                System.out.printf("| %-10d | %-10d | %-10.2f | %-10.2f |\n",
                        current.data.getMaHoaDon(),
                        current.data.getMaDichVu(),
                        current.data.getSoLuong(),
                        current.data.getThanhTien());
                return current;
            }
            current = current.next;
        }

        System.out.println("Không tìm thấy hoá đơn có mã: " + maHoaDon);
        return null;
    }

    // In danh sách các chi tiết hóa đơn
    public void printListChiTietHoaDon() {
        NodeChiTietHoaDon current = first;
        System.out.println("+------------+------------+------------+------------+");
        System.out.println("| MaHoaDon   | MaDichVu   | SoLuong    | ThanhTien  |");
        System.out.println("+------------+------------+------------+------------+");
        while (current != null) {
            System.out.printf("| %-10d | %-10d | %-10.2f | %-10.2f |\n",
                    current.data.getMaHoaDon(),
                    current.data.getMaDichVu(),
                    current.data.getSoLuong(),
                    current.data.getThanhTien());
            current = current.next;
        }
        System.out.println("+------------+------------+------------+------------+");
    }

    // Cập nhật chi tiết hóa đơn
    public void updateChiTietHoaDon(int maHoaDon, ChiTietHoaDon updatedChiTiet) {
        NodeChiTietHoaDon current = searchChiTietHoaDon(maHoaDon);
        if (current == null) {
            System.out.println("Không tìm thấy hóa đơn có mã: " + maHoaDon);
            return;
        }

        // Cập nhật thông tin hóa đơn
        current.data.setSoLuong(updatedChiTiet.getSoLuong());
        current.data.setDonGia(updatedChiTiet.getDonGia());
        current.data.setThanhTien(updatedChiTiet.getThanhTien());

        ChiTietHoaDonService.updateChiTietHoaDon(current.data, maHoaDon, current.data.getMaDichVu());  // Cập nhật trong cơ sở dữ liệu
        System.out.println("Cập nhật hóa đơn có mã " + maHoaDon + " thành công.");
    }
}

