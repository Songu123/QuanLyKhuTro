package project.ctdl;

import project.quanlykhutro.controller.ChiTietHoaDonController;
import project.quanlykhutro.controller.DichVuController;
import project.quanlykhutro.models.DichVu;
import project.quanlykhutro.models.DichVu;
import project.quanlykhutro.services.DichVuService;
import project.quanlykhutro.services.DichVuService;

import javax.xml.soap.Node;

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
//        System.out.println("Them dichVu thanh cong!");
    }

    public NodeDichVu searchDichVu(int maDichVu) {
        NodeDichVu current = first;
        boolean found = false;

        while (current != null) {
            if (current.data.getMaDichVu() == maDichVu) {
                System.out.printf("| %-10d | %-20s | %-10.2f |\n",
                        current.data.getMaDichVu(),
                        current.data.getTenDichVu(),
                        current.data.getDonGia());
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

    public void hienThiDichVu(NodeDichVu nodeDichVu) {
        System.out.printf("| %-10d | %-20s | %-10.2f |\n",
                nodeDichVu.data.getMaDichVu(),
                nodeDichVu.data.getTenDichVu(),
                nodeDichVu.data.getDonGia());
    }

    public NodeDichVu searchDichVuDeQuy(int maDichVu) {
        return searchDichVuDeQuy(first, maDichVu);
    }

    //    Dùng thuật toán đệ quy để tìm kiếm
    public NodeDichVu searchDichVuDeQuy(NodeDichVu nodeDichVu, int maDichVu) {
        if (nodeDichVu == null) {
            return null;
        }

        if (nodeDichVu.data.getMaDichVu() == maDichVu) {
            return nodeDichVu; // Trả về nodeDichVu nếu tìm thấy
        }

        return searchDichVuDeQuy(nodeDichVu.next, maDichVu); // Tiếp tục tìm kiếm bằng đệ quy
    }

    public void duyetListDichVu(int maHoaDon) {
        NodeDichVu current = first;
        while (current != null) {
            if (current.data.getTrangThai().equals("Hoạt Động")) {
                ChiTietHoaDonController.nhapSoLuongDichVu(current, maHoaDon);
            }
            current = current.next;
        }
    }

    public void printListDichVu() {
        NodeDichVu current = first; // Giả sử 'first' là nút đầu tiên của danh sách
        while (current != null) {
            while (current != null) {
                System.out.printf("| %-10d | %-20s | %10.2f | %-10s |\n",
                        current.data.getMaDichVu(),
                        current.data.getTenDichVu(),
                        current.data.getDonGia(),
                        current.data.getTrangThai());
                current = current.next;
            }

            System.out.println("+------------+----------------------+------------+------------+");

            current = current.next; // Di chuyển đến nút tiếp theo
        }
        System.out.println("+------------+----------------------+------------+------------+"); // In dòng dưới cùng
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
        System.out.println("Xóa dịch vụ có mã " + maDichVu + " thành công.");
    }

    public void updateDichVu(int maDichVu, DichVu dichVuMoi) {
        NodeDichVu current = searchDichVuDeQuy(maDichVu);

        if (current == null) {
            System.out.println("Không tìm thấy dịch vụ có mã: " + maDichVu);
            return;
        }

        // Cập nhật thông tin của phòng hiện tại với dữ liệu mới
        current.data.setTenDichVu(dichVuMoi.getTenDichVu());
        current.data.setDonGia(dichVuMoi.getDonGia());

        System.out.println("Cập nhật phòng có mã " + maDichVu + " thành công.");
    }

    //    Dùng thuật toán sắp xếp nổi bọt để sắp xếp
    public void bubbleSort() {
        if (first == null) return;

        boolean swapped;
        NodeDichVu current;

        do {
            swapped = false;
            current = first;

            while (current.next != null) {
                if (current.data.getDonGia() > current.next.data.getDonGia()) {
                    // Đổi chỗ dữ liệu của hai node
                    int maDichVu = current.data.getMaDichVu();
                    String tenDichVu = current.data.getTenDichVu();
                    float donGia = current.data.getDonGia();

                    current.data.setMaDichVu(current.next.data.getMaDichVu());
                    current.data.setTenDichVu(current.next.data.getTenDichVu());
                    current.data.setDonGia(current.next.data.getDonGia());

                    current.next.data.setMaDichVu(maDichVu);
                    current.next.data.setTenDichVu(tenDichVu);
                    current.next.data.setDonGia(donGia);
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    public void updateStatus(int id, String trangThai) {
        searchDichVuDeQuy(id).data.setTrangThai(trangThai);
    }
}
