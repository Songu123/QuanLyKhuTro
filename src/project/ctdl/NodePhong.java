package project.ctdl;

import project.quanlykhutro.models.Phong;

public class NodePhong {
    public Phong data;
    NodePhong prev;
    NodePhong next;

    public NodePhong(Phong data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
