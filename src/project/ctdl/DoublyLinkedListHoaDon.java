package project.ctdl;

import project.quanlykhutro.models.HoaDon;
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
    }

    public void addHoaDon(HoaDon hoaDon) {
        NodeHoaDon newNode = new NodeHoaDon(hoaDon);
        if (first == null && last == null){
            first = newNode;
            last = newNode;
        }else  {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
            System.out.println("Them hoa don thanh cong!");
        }

        size++;
        hoaDonService.addHoaDon(hoaDon);
    }


}
