package project.ctdl;

import project.quanlykhutro.models.DichVu;

public class NodeDichVu {
    DichVu data;
    NodeDichVu prev;
    NodeDichVu next;

    public NodeDichVu(DichVu data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
