package project.ctdl;

import project.quanlykhutro.models.HoaDon;
import project.quanlykhutro.models.HoaDon;
import project.quanlykhutro.services.HoaDonService;
import project.quanlykhutro.services.HoaDonService;

public class DoublyLinkedListHoaDon {
    private NodeHoaDon first, last;
    private int size;
    private HoaDonService hoaDonService;


    public DoublyLinkedListHoaDon() {
        this.first = null;
        this.last = null;
        this.size = 0;
        this.hoaDonService = new HoaDonService();
        loadDataFromDatabase();
    }

    // Tải dữ liệu từ MySQL vào danh sách liên kết đôi
    public void loadDataFromDatabase() {
        try {
            HoaDonService.getListAllHoaDon(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addHoaDon(HoaDon hoaDon) {
        NodeHoaDon newNode = new NodeHoaDon(hoaDon);
        if (first == null && last == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
            System.out.println("Tạo hoá đơn thành công!");
        }

        size++;
    }

    public int getSize() {
        return size;
    }


    public NodeHoaDon searchHoaDon(int maHoaDon) {
        NodeHoaDon current = first;
        boolean found = false;

        // Hiển thị tiêu đề bảng
        System.out.println("+------------+----------------------+------------+------------+------------+---------------+");
        System.out.printf("| %-10s | %-20s | %-10s | %-10s | %-10s | %-15s |\n",
                "MaHoaDon", "MaHopDong", "NgayPhatHanh", "NgayDenHan", "TongTien", "TrangThai");
        System.out.println("+------------+----------------------+------------+------------+------------+---------------+");

        while (current != null) {
            if (current.data.getMaHoaDon() == maHoaDon) {
                System.out.printf("| %-10d | %-20d | %-10s | %-10s | %-10.2f | %-15s |\n",
                        current.data.getMaHoaDon(),
                        current.data.getMaHopDong(),
                        current.data.getNgayPhatHanh(),
                        current.data.getNgayDenHan(),
                        current.data.getTongTien(),
                        current.data.getTrangThai());
                found = true;
                break;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("Không tìm thấy hoá đơn có mã: " + maHoaDon);
        }

        System.out.println("+------------+----------------------+------------+------------+------------+---------------+");
        return current;
    }


    public void printListHoaDon() {
        NodeHoaDon current = first;

        // Hiển thị tiêu đề bảng
        System.out.println("+------------+----------------------+------------+------------+------------+---------------+");
        System.out.printf("| %-10s | %-20s | %-10s | %-10s | %-10s | %-15s |\n",
                "MaHoaDon", "MaHopDong", "NgayPhatHanh", "NgayDenHan", "TongTien", "TrangThai");
        System.out.println("+------------+----------------------+------------+------------+------------+---------------+");

        while (current != null) {
            System.out.printf("| %-10d | %-20d | %-10s | %-10s | %-10.2f | %-15s |\n",
                    current.data.getMaHoaDon(),
                    current.data.getMaHopDong(),
                    current.data.getNgayPhatHanh(),
                    current.data.getNgayDenHan(),
                    current.data.getTongTien(),
                    current.data.getTrangThai());

            current = current.next;
        }

        System.out.println("+------------+----------------------+------------+------------+------------+---------------+");
    }


    public void deleteHoaDon(int maHoaDon) {
        NodeHoaDon current = searchHoaDon(maHoaDon);

        // Nếu không tìm thấy phòng với mã tương ứng
        if (current == null) {
            System.out.println("Không tìm thấy hoá đơn có mã: " + maHoaDon);
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
        System.out.println("Xóa hoá đơn có mã " + maHoaDon + " thành công.");
    }

    public void updateHoaDon(int maHoaDon, String trangThai) {
        NodeHoaDon current = searchHoaDon(maHoaDon);

        if (current == null) {
            System.out.println("Không tìm thấy phòng có mã: " + maHoaDon);
            return;
        }

        // Cập nhật thông tin của phòng hiện tại với dữ liệu mới
        current.data.setTrangThai(trangThai);

        System.out.println("Cập nhật hoá đơn có mã " + maHoaDon + " thành công.");
    }

//    Hàm lấy danh sách nợ
public void printHoaDonNo() {
    NodeHoaDon current = first;
    System.out.println("+------------+----------------------+------------+");
    while (current != null) {
        String trangThai = current.data.getTrangThai();

        // Kiểm tra nếu trạng thái là "Quá Hạn" hoặc "Chưa Thanh Toán"
        if (trangThai.equalsIgnoreCase("Quá Hạn") || trangThai.equalsIgnoreCase("Chưa Thanh Toán")) {
            System.out.printf("| %-10d | %-20d | %-10s | %-10s | %-10.2f | %-15s |\n",
                    current.data.getMaHoaDon(),
                    current.data.getMaHopDong(),
                    current.data.getNgayPhatHanh(),
                    current.data.getNgayDenHan(),
                    current.data.getTongTien(),
                    current.data.getTrangThai());
        }

        current = current.next;
    }
    System.out.println("+------------+----------------------+------------+");
}


}
