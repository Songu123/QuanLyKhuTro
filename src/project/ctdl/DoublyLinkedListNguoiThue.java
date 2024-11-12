package project.ctdl;

import project.quanlykhutro.controller.NguoiThueController;
import project.quanlykhutro.models.NguoiThue;
import project.quanlykhutro.services.NguoiThueService;
import project.quanlykhutro.services.NguoiThueService;

public class DoublyLinkedListNguoiThue {
    private NodeNguoiThue first, last;
    private int size;

    public DoublyLinkedListNguoiThue() {
        this.first = null;
        this.last = null;
        this.size = 0;
        loadDataFromDatabase();
    }

    public int getSize() {
        return size;
    }


    // Tải dữ liệu từ MySQL vào danh sách liên kết đôi
    public void loadDataFromDatabase() {
        try {
            NguoiThueService.getListAllNguoiThue(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addLast(NguoiThue nguoiThue) {
        NodeNguoiThue newNode = new NodeNguoiThue(nguoiThue);
        if (first == null && last == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        size++;
//        System.out.println("Them nguoiThue thanh cong!");
    }

    public NodeNguoiThue searchNguoiThue(int maNguoiThue) {
        NodeNguoiThue current = first;
        boolean found = false;
        NguoiThueController.hienThiTieuDeNguoiThue();
        while (current != null) {
            if (current.data.getMaNguoiThue() == maNguoiThue) {
                System.out.printf("| %-10d | %-20s | %-10s | %-8s | %-35s | %-12s | %-12s |\n",
                        current.data.getMaNguoiThue(),
                        current.data.getTen(),
                        current.data.getNgaySinh().toString(),
                        current.data.getGioiTinh(),
                        current.data.getDiaChi(),
                        current.data.getSoDienThoai(),
                        current.data.getTrangThai()); // Thêm trạng thái
                found = true;
                break;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("Không tìm thấy người thuê có mã: " + maNguoiThue);
            return null;
        }

        return current;
    }


    public void printListNguoiThue() {
        NodeNguoiThue current = first;
        while (current != null) {
            System.out.printf("| %-10d | %-20s | %-10s | %-8s | %-35s | %-12s | %-12s |\n",
                    current.data.getMaNguoiThue(),
                    current.data.getTen(),
                    current.data.getNgaySinh().toString(),
                    current.data.getGioiTinh(),
                    current.data.getDiaChi(),
                    current.data.getSoDienThoai(),
                    current.data.getTrangThai());

            current = current.next;
        }
        System.out.println("+------------+----------------------+------------+----------+------------------------------------+--------------+--------------+");
    }



    public void deleteNguoiThue(int maNguoiThue) {
        NodeNguoiThue current = searchNguoiThue(maNguoiThue);

        // Nếu không tìm thấy phòng với mã tương ứng
        if (current == null) {
            System.out.println("Không tìm thấy phòng có mã: " + maNguoiThue);
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
        System.out.println("Xóa phòng có mã " + maNguoiThue + " thành công.");
    }

    public void updateNguoiThue(int maNguoiThue, NguoiThue nguoiThueMoi) {
        NodeNguoiThue current = searchNguoiThue(maNguoiThue);

        if (current == null) {
            System.out.println("Không tìm thấy phòng có mã: " + maNguoiThue);
            return;
        }

        // Cập nhật thông tin của phòng hiện tại với dữ liệu mới
        current.data.setTen(nguoiThueMoi.getTen());
        current.data.setNgaySinh(nguoiThueMoi.getNgaySinh());
        current.data.setGioiTinh(nguoiThueMoi.getGioiTinh());
        current.data.setDiaChi(nguoiThueMoi.getDiaChi());
        current.data.setSoDienThoai(nguoiThueMoi.getSoDienThoai());
//        current.data.setTrangThai(nguoiThueMoi.getTrangThai());

        System.out.println("Cập nhật phòng có mã " + maNguoiThue + " thành công.");
    }

    public void updateTrangThaiNguoiThue(int maNguoiThue, String trangThai) {
        NodeNguoiThue current = searchNguoiThue(maNguoiThue);

        if (current == null) {
            System.out.println("Không tìm thấy phòng có mã: " + maNguoiThue);
            return;
        }

        current.data.setTrangThai(trangThai);

        System.out.println("Cập nhật phòng có mã " + maNguoiThue + " thành công.");
    }

    public boolean checkNguoiThue(int maNguoiThue) {
        NodeNguoiThue current = first;
        while (current != null) {
            if (current.data.getMaNguoiThue() == maNguoiThue &&
                    current.data.getTrangThai().equalsIgnoreCase("Đang Thuê")) {
                return false; // Người thuê đã thuê phòng, không thể thuê thêm
            }
            current = current.next;
        }
        return true; // Người thuê chưa thuê phòng
    }

}
