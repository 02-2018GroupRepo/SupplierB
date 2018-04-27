package bootcamp.controller;


import bootcamp.model.Payment.Payment;
import bootcamp.model.invoice.Invoice;
import bootcamp.model.order.Order;
import bootcamp.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class InvoiceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    InvoiceService invoiceService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public Invoice receiveOrder(@RequestBody Order order) {
        return invoiceService.receiveOrder(order);
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public Boolean receivePayment(@RequestBody Payment invoicePayment) {
        return invoiceService.receivePayment(invoicePayment);
    }

    @RequestMapping(value = "/shipment/new", method = RequestMethod.POST)
    public double receiveInvoice(@RequestBody Invoice invoice) {
        return invoiceService.receiveInvoice(invoice);
    }

    @RequestMapping("/company")
    public BigDecimal[] getWholesaleSum(){
        return invoiceService.getWholesaleSum();
    }


}
