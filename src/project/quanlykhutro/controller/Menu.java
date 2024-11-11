package project.quanlykhutro.controller;

public class Menu {

    public static void menuQuanLyKhuTro() {
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                QUẢN LÝ KHU TRỌ                    |");
        System.out.println("+---------------------------------------------------+");
        System.out.println("| 1. Quản lý phòng                                  |");
        System.out.println("| 2. Quản lý người thuê                             |");
        System.out.println("| 3. Quản lý dịch vụ                                |");
        System.out.println("| 4. Quản lý thuê phòng                             |");
//        System.out.println("| 5. Quản lý trả phòng                              |");
        System.out.println("| 5. Quản lý hoá đơn                                |");
        System.out.println("| 6. Báo cáo và thống kê                            |");
        System.out.println("| 0. Thoát                                          |");
        System.out.println("+---------------------------------------------------+");
    }

    public static void menuQuanLyPhong() {
        System.out.println("+---------------------------------------------------+");
        System.out.println("|                  QUẢN LÝ PHÒNG                    |");
        System.out.println("+---------------------------------------------------+");
        System.out.println("| 1. Thêm phòng                                     |");
        System.out.println("| 2. Sửa phòng                                      |");
        System.out.println("| 3. Cập nhật trạng thái phòng                      |");
        System.out.println("| 4. Thống kê số phòng trống                        |");
        System.out.println("| 5. Tìm kiếm phòng theo trạng thái                 |");
        System.out.println("| 6. Tìm kiếm phòng theo ID                         |");
        System.out.println("| 7. Xem danh sách phòng                            |");
        System.out.println("| 8. Sắp xếp phòng theo giá thuê (Merge Sort)       |");
        System.out.println("| 0. Thoát                                          |");
        System.out.println("+---------------------------------------------------+");
    }


    public static void menuQuanLyNguoiThue() {
        System.out.println("+---------------------------------------------------+");
        System.out.println("|               QUẢN LÝ NGƯỜI THUÊ                  |");
        System.out.println("+---------------------------------------------------+");
        System.out.println("| 1. Thêm người thuê                                |");
        System.out.println("| 2. Sửa người thuê                                 |");
        System.out.println("| 3. Xoá người thuê                                 |");
        System.out.println("| 4. Cập nhật trạng thái người thuê                 |");
        System.out.println("| 5. Tìm kiếm người thuê                            |");
        System.out.println("| 6. Hiển thị danh sách người thuê                  |");
        System.out.println("| 0. Thoát                                          |");
        System.out.println("+---------------------------------------------------+");
    }

    public static void menuQuanLyDichVu() {
        System.out.println("+---------------------------------------------------+");
        System.out.println("|               QUẢN LÝ DỊCH VỤ                     |");
        System.out.println("+---------------------------------------------------+");
        System.out.println("| 1. Thêm dịch vụ                                   |");
        System.out.println("| 2. Sửa dịch vụ                                    |");
        System.out.println("| 3. Cập nhật trạng thái dịch vụ                    |");
        System.out.println("| 4. Tìm kiếm dịch vụ(Đệ quy)                       |");
        System.out.println("| 5. Hiển thị danh sách dịch vụ                     |");
        System.out.println("| 6. Sắp xếp danh sách dịch vụ theo giá(Bubble Sort)|");
        System.out.println("| 0. Thoát                                          |");
        System.out.println("+---------------------------------------------------+");
    }

    public static void menuQuanLyHopDong() {
        System.out.println("+---------------------------------------------------+");
        System.out.println("|              QUẢN LÝ HỢP ĐỒNG                     |");
        System.out.println("+---------------------------------------------------+");
        System.out.println("| 1. Tạo hợp đồng mới                               |");
        System.out.println("| 2. Xem danh sách hợp đồng                         |");
        System.out.println("| 3. Gia hạn hợp đồng                               |");
        System.out.println("| 4. Tìm kiếm hợp đồng                              |");
        System.out.println("| 5. Chỉnh sửa hợp đồng                             |");
        System.out.println("| 6. Xóa hợp đồng                                   |");
        System.out.println("| 7. Xuất thông tin hợp đồng ra file                |");
        System.out.println("| 8. Xem chi tiết hợp đồng                          |");
        System.out.println("| 0. Quay lại                                       |");
        System.out.println("+---------------------------------------------------+");
    }

    public static void menuQuanLyHoaDon() {
        System.out.println("+---------------------------------------------------+");
        System.out.println("|               QUẢN LÝ HÓA ĐƠN                     |");
        System.out.println("+---------------------------------------------------+");
        System.out.println("| 1. Tạo hóa đơn hàng tháng                         |");
        System.out.println("| 2. Xem chi tiết hóa đơn                           |");
        System.out.println("| 3. Cập nhật trạng thái thanh toán                 |");
        System.out.println("| 4. Xem danh sách hóa đơn                          |");
        System.out.println("| 5. Thống kê công nợ                               |");
        System.out.println("| 0. Quay lại                                       |");
        System.out.println("+---------------------------------------------------+");
    }
}
