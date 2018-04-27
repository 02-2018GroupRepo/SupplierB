package bootcamp.service;


import bootcamp.dao.InvoiceDao;
import bootcamp.model.Payment.Payment;
import bootcamp.model.invoice.Invoice;
import bootcamp.model.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InvoiceService {

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private InvoiceDao invoiceDao;

    public Invoice receiveOrder (Order order)  {
        return invoiceDao.receiveOrder(order);
    }

    public Boolean receivePayment (Payment invoicePayment) { return invoiceDao.receivePayment(invoicePayment); }

    public double receiveInvoice(Invoice invoice) { return invoiceDao.receiveInvoice(invoice); }

    public BigDecimal[] getWholesaleSum(){
        return invoiceDao.getWholesaleSum();
    }


}
