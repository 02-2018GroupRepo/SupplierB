package bootcamp.dao;

import org.springframework.stereotype.Component;

@Component
public class FinanceDao {

//    private final String GET_WHOLESALE_PRICE = "SELECT product.wholesale_price FROM product INNER JOIN inventory ON inventory.id = product.id";
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    private Finance finance = new Finance();
//    private Invoice invoice = new Invoice();
//
//    public BigDecimal[] getWholesaleSum(){
//        BigDecimal finalTotal = new BigDecimal(0.0);
//        BigDecimal totalCash = finance.getTotalBalance();
//        List<BigDecimal> listOfWholesalePrice = jdbcTemplate.queryForList(GET_WHOLESALE_PRICE, BigDecimal.class);
//        for (BigDecimal total : listOfWholesalePrice) {
//            //System.out.println(total);
//            finalTotal = finalTotal.add(total);
//        }
//        BigDecimal [] arr = new BigDecimal[]{totalCash, finalTotal};
//        return arr;
//    }

//    public Boolean receivePayment(Payment invoicePayment) {
//        int invoiceId = invoicePayment.getInvoiceId();
//        System.out.println(invoiceId);
//        BigDecimal total = invoice.getInvocieIdTotal(invoiceId);
//        System.out.println(total);
//        if(total == invoicePayment.getTotal()) {
//            finance.setDeposit(invoicePayment.getTotal());
//            return true;
//        } else {
//            return false;
//        }
//    }
}
