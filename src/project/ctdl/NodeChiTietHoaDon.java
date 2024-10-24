package project.ctdl;

import project.quanlykhutro.models.ChiTietHoaDon;

public class NodeChiTietHoaDon {
    ChiTietHoaDon data;
    NodeChiTietHoaDon prev;
    NodeChiTietHoaDon next;

    public NodeChiTietHoaDon(ChiTietHoaDon data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
