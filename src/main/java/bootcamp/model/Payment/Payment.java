package bootcamp.model.Payment;

import java.math.BigDecimal;

public class Payment {

    private int invoiceId;
    private BigDecimal total;

    public Payment() {}

    public Payment(int invoiceId, BigDecimal total) {
        this.invoiceId = invoiceId;
        this.total = total;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
