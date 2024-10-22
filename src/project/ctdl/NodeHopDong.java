package project.ctdl;

import project.quanlykhutro.models.HopDong;

public class NodeHopDong {
    HopDong data;
    NodeHopDong prev;
    NodeHopDong next;

    public NodeHopDong(HopDong data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
