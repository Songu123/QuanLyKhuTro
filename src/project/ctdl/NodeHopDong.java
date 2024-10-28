package project.ctdl;

import project.quanlykhutro.models.HopDong;

public class NodeHopDong {
    public HopDong data;
    NodeHopDong prev;
    NodeHopDong next;

    public NodeHopDong(HopDong data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
