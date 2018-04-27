package bootcamp.dao;

import bootcamp.model.Finance.Finance;
import bootcamp.model.Payment.Payment;
import bootcamp.model.inventory.Inventory;
import bootcamp.model.invoice.Invoice;
import bootcamp.model.order.Order;
import bootcamp.model.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Component
public class InvoiceDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;

    private final String EXISTING_INVENTORY = "SELECT * from inventory";
    private final String GET_PRODUCT = "SELECT id, name, description, retail_price, FROM product";
    private final String GET_PRODUCT_BY_ID = GET_PRODUCT + " where id = ?";
    private final String GET_WHOLESALE_PRICE = "SELECT product.wholesale_price FROM product INNER JOIN inventory ON inventory.id = product.id";

    private Finance finance = new Finance();
    private Invoice invoiceForOrderGlobal = new Invoice();

    public Invoice receiveOrder(Order order) {
        List<Inventory> existingProduct = jdbcTemplate.query(EXISTING_INVENTORY, new BeanPropertyRowMapper<>(Inventory.class));
        Invoice invoiceForOrder = new Invoice();
        Product prod;
        Random rand = new Random();
        int randomId = rand.nextInt(1000);
        BigDecimal totalCost;
        BigDecimal prodRetailPrice;

        for (Inventory inven : existingProduct) {
            if (inven.getId() == order.getId()) {
                //prod = jdbcTemplate.queryForObject(GET_PRODUCT_BY_ID, new Object[] {order.getId()}, new BeanPropertyRowMapper<>(Product.class));
                prod = jdbcTemplate.queryForObject("SELECT id, name, description, retail_price FROM product WHERE id = " + order.getId(), new BeanPropertyRowMapper<>(Product.class));
                prodRetailPrice = prod.getRetail_price();
                if (inven.getNumber_available() >= order.getQuantity()) {
                    System.out.println(order.getQuantity());
                    invoiceForOrderGlobal.setCount(order.getQuantity());
                    invoiceForOrderGlobal.setInvoiceCount(randomId, order.getQuantity());
                    totalCost = prodRetailPrice.multiply(BigDecimal.valueOf(order.getQuantity()));
                    System.out.println("Total cost " + totalCost);
                    //invoiceForOrder.setInvoiceIdToTotal(randomId, totalCost);
                } else {
                    invoiceForOrderGlobal.setCount(inven.getNumber_available());
                    invoiceForOrderGlobal.setInvoiceCount(randomId, inven.getNumber_available());
                    totalCost = prodRetailPrice.multiply(BigDecimal.valueOf(inven.getNumber_available()));
                    System.out.println("TotalCost is " + totalCost);
                }
                invoiceForOrderGlobal.setInvoiceIdToTotal(randomId, totalCost);
                invoiceForOrderGlobal.setInvoiceIdProdId(randomId, order.getId());
                invoiceForOrderGlobal.setProduct(prod);
                invoiceForOrderGlobal.setId(randomId);
            }
        }
        return invoiceForOrderGlobal;
    }

    public Boolean receivePayment(Payment invoicePayment) {
        List<Inventory> existingProduct = jdbcTemplate.query(EXISTING_INVENTORY, new BeanPropertyRowMapper<>(Inventory.class));
        int invoiceId = invoicePayment.getInvoiceId();
        BigDecimal total = invoiceForOrderGlobal.getInvocieIdTotal(invoiceId);
        if (total.compareTo(invoicePayment.getTotal()) == 0) {
            finance.setDeposit(invoicePayment.getTotal());
            int quantity = invoiceForOrderGlobal.getInvoiceCount(invoiceId);
            int prodId = invoiceForOrderGlobal.getProdId(invoiceId);
            int existingQuantity = 0;
            for (Inventory inven : existingProduct) {
                if(inven.getId() == prodId) {
                    existingQuantity = inven.getNumber_available();
                }
            }
            existingQuantity = existingQuantity - quantity;
            jdbcTemplate.update("UPDATE inventory SET number_available= " + existingQuantity  + " WHERE id= " + prodId);
            System.out.println(finance.getTotalBalance());
            return true;
        } else {
            return false;
        }
    }

    public double receiveInvoice(Invoice invoice) {
        Product prod = invoice.getProduct();
        BigDecimal price = prod.getRetail_price();
        double priceInDouble = price.doubleValue();
        int quantity = invoice.getCount();
        double totalDue = priceInDouble * quantity;
        finance.setWithdrawal(BigDecimal.valueOf(totalDue));
        System.out.println(finance.getTotalBalance());
        return totalDue;
    }

    public BigDecimal[] getWholesaleSum(){
        BigDecimal finalTotal = new BigDecimal(0.0);
        BigDecimal totalCash = finance.getTotalBalance();
        List<BigDecimal> listOfWholesalePrice = jdbcTemplate.queryForList(GET_WHOLESALE_PRICE, BigDecimal.class);
        for (BigDecimal total : listOfWholesalePrice) {
            //System.out.println(total);
            finalTotal = finalTotal.add(total);
        }
        BigDecimal [] arr = new BigDecimal[]{totalCash, finalTotal};
        return arr;
    }

    @PostConstruct
    public void initDataSource() {
        jdbcTemplate = new JdbcTemplate((javax.sql.DataSource) dataSource);
    }
}