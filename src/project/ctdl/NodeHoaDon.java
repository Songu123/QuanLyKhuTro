package project.ctdl;

import project.quanlykhutro.models.HoaDon;

public class NodeHoaDon {
    HoaDon data;
    NodeHoaDon prev;
    NodeHoaDon next;

    public NodeHoaDon(HoaDon data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
