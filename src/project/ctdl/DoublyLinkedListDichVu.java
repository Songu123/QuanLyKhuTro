package project.ctdl;

import project.quanlykhutro.controller.ChiTietHoaDonController;
import project.quanlykhutro.controller.DichVuController;
import project.quanlykhutro.models.DichVu;
import project.quanlykhutro.models.DichVu;
import project.quanlykhutro.services.DichVuService;
import project.quanlykhutro.services.DichVuService;

public class DoublyLinkedListDichVu {
    private NodeDichVu first, last;
    private int size;
    private DichVuService dichVuService;

    public DoublyLinkedListDichVu() {
        this.first = null;
        this.last = null;
        this.size = 0;
        this.dichVuService = new DichVuService();
        loadDataFromDatabase();
    }

    public int getSize() {
        return size;
    }


    // Tải dữ liệu từ MySQL vào danh sách liên kết đôi
    public void loadDataFromDatabase() {
        try {
            DichVuService.getListAllDichVu(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addLast(DichVu dichVu) {
        NodeDichVu newNode = new NodeDichVu(dichVu);
        if (first == null && last == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        size++;
        System.out.println("Them dichVu thanh cong!");
    }

    public NodeDichVu searchDichVu(int maDichVu) {
        NodeDichVu current = first;
        boolean found = false;

        while (current != null) {
            if (current.data.getMaDichVu() == maDichVu) {
//                System.out.printf("| %-10d | %-20s | %-10.2f |\n",
//                        current.data.getMaDichVu(),
//                        current.data.getTenDichVu(),
//                        current.data.getDonGia());
                found = true;
                break;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("Không tìm thấy người thuê có mã: " + maDichVu);
            return null;
        }

        return current;
    }

    public void duyetListDichVu(int maHoaDon) {
        NodeDichVu current = first;
        while (current != null) {
            ChiTietHoaDonController.nhapSoLuongDichVu(current, maHoaDon);
            current = current.next;
        }
    }

    public void printListDichVu() {
        NodeDichVu current = first;
        while (current != null) {
            System.out.printf("| %-10d | %-20s | %-10.2f |\n",
                    current.data.getMaDichVu(),
                    current.data.getTenDichVu(),
                    current.data.getDonGia());

            current = current.next;
        }
        System.out.println("+------------+----------------------+------------+");
    }

    public void deleteDichVu(int maDichVu) {
        NodeDichVu current = searchDichVu(maDichVu);

        // Nếu không tìm thấy phòng với mã tương ứng
        if (current == null) {
            System.out.println("Không tìm thấy phòng có mã: " + maDichVu);
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
        System.out.println("Xóa phòng có mã " + maDichVu + " thành công.");
    }

    public void updateDichVu(int maDichVu, DichVu dichVuMoi) {
        NodeDichVu current = searchDichVu(maDichVu);

        if (current == null) {
            System.out.println("Không tìm thấy phòng có mã: " + maDichVu);
            return;
        }

        // Cập nhật thông tin của phòng hiện tại với dữ liệu mới
        current.data.setTenDichVu(dichVuMoi.getTenDichVu());
        current.data.setDonGia(dichVuMoi.getDonGia());

        System.out.println("Cập nhật phòng có mã " + maDichVu + " thành công.");
    }
}
