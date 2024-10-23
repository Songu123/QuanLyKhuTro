package project.ctdl;

import project.quanlykhutro.models.NguoiThue;

public class NodeNguoiThue {
    NguoiThue data;
    NodeNguoiThue next;
    NodeNguoiThue prev;

    public NodeNguoiThue(NguoiThue data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

}
