package ifb2021.paf.day33;

import ifb2021.paf.day33.models.PurchaseOrder;

public class OrderTooLargeException extends Exception {
    private PurchaseOrder po;

    public OrderTooLargeException() { super(); }
    public OrderTooLargeException(String msg) {
        super(msg);
    }
    public PurchaseOrder getPo() {
        return po;
    }
    public void setPo(PurchaseOrder po) {
        this.po = po;
    }
}
