package project.ctdl;

import project.quanlykhutro.models.DichVu;
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
    }

    public void addDichVu(DichVu dichVu) {
        NodeDichVu newNode = new NodeDichVu(dichVu);
        if (first == null && last == null) {
            first = newNode;
            last = newNode;
        }else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
            System.out.println("Them dich vu thanh cong");
        }

        size++;
        dichVuService.addDichVu(dichVu);
    }
}
