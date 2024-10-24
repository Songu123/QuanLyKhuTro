package project.ctdl;

import project.quanlykhutro.models.DichVu;

public class NodeDichVu {
    public DichVu data;
    public NodeDichVu prev;
    public NodeDichVu next;

    public NodeDichVu(DichVu data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
