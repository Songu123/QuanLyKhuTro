package project.ctdl;

import project.quanlykhutro.models.HopDong;
import project.quanlykhutro.models.HopDong;
import project.quanlykhutro.services.HopDongService;

import javax.xml.soap.Node;
import java.lang.ref.PhantomReference;

public class DoublyLinkedListHopDong {
    private NodeHopDong first, last;
    private int size;
    private HopDongService hopDongService;

    public DoublyLinkedListHopDong() {
        this.first = null;
        this.last = null;
        this.size = 0;
        loadDataFromDatabase();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void loadDataFromDatabase() {
        HopDongService.getListAllHopDong(this);
    }

    public void addLast(HopDong hopDong) {
        NodeHopDong newNode = new NodeHopDong(hopDong);
        if (first == null && last == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        size++;
        System.out.println("Thêm hợp đồng thành công!");
    }

    public NodeHopDong searchHopDong(int maHopDong) {
        NodeHopDong current = first;
        boolean found = false;

        while (current != null) {
            if (current.data.getMaHopDong() == maHopDong) {
                System.out.printf("| %-10d | %-10d | %-12d | %-10s | %-10s | %-12.2f | %-10.2f |\n",
                        current.data.getMaHopDong(),
                        current.data.getMaPhong(),
                        current.data.getMaNguoiThue(),
                        current.data.getNgayBatDau().toString(),
                        current.data.getNgayKetThuc().toString(),
                        current.data.getGiaThueHopDong(),
                        current.data.getTienCoc());

                found = true;
                break;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("Không tìm thấy phòng có mã: " + maHopDong);
            return null;
        }

        return current;
    }

    public void printListHopDong() {
        NodeHopDong current = first;
        while (current != null) {
            System.out.printf("| %-10d | %-10d | %-12d | %-10s | %-10s | %-12.2f | %-10.2f |\n",
                    current.data.getMaHopDong(),
                    current.data.getMaPhong(),
                    current.data.getMaNguoiThue(),
                    current.data.getNgayBatDau().toString(),
                    current.data.getNgayKetThuc().toString(),
                    current.data.getGiaThueHopDong(),
                    current.data.getTienCoc());

            current = current.next;
        }
    }

    public void deleteHopDong(int maHopDong) {
        NodeHopDong current = searchHopDong(maHopDong);

        // Nếu không tìm thấy phòng với mã tương ứng
        if (current == null) {
            System.out.println("Không tìm thấy phòng có mã: " + maHopDong);
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
        System.out.println("Xóa phòng có mã " + maHopDong + " thành công.");
    }

    public void updateHopDong(int maHopDong, HopDong hopDongMoi) {
        NodeHopDong current = searchHopDong(maHopDong);

        if (current == null) {
            System.out.println("Không tìm thấy phòng có mã: " + maHopDong);
            return;
        }

        // Cập nhật thông tin của phòng hiện tại với dữ liệu mới
        current.data.setMaPhong(hopDongMoi.getMaPhong());
        current.data.setMaNguoiThue(hopDongMoi.getMaNguoiThue());
        current.data.setNgayBatDau(hopDongMoi.getNgayBatDau());
        current.data.setNgayKetThuc(hopDongMoi.getNgayKetThuc());
        current.data.setGiaThueHopDong(hopDongMoi.getGiaThueHopDong());
        current.data.setTienCoc(hopDongMoi.getTienCoc());

        System.out.println("Cập nhật phòng có mã " + maHopDong + " thành công.");
    }


}
