package project.quanlykhutro.controller;

import project.quanlykhutro.models.NguoiThue;

import java.util.Scanner;

public class QuanLyKhuTroController {
    public static Scanner sc = new Scanner(System.in);
    public static void run() {
        boolean running = true;
        while (running) {
            Menu.menuQuanLyKhuTro();
            System.out.println("Vui lòng chọn chức năng (0-6): ");
            int chon = Integer.parseInt(sc.nextLine());
            switch (chon) {
                case 1:
                    PhongController.run();
                    break;
                case 2:
                    NguoiThueController.run();
                    break;
                case 3:
                    DichVuController.run();
                    break;
                case 4:
                    HopDongController.run();
                    break;
                case 0:
                    running = false;
                default:
                    System.out.println("Nhập sai! Vui lòng nhập lại (0-6)!");
            }
        }
    }

}
