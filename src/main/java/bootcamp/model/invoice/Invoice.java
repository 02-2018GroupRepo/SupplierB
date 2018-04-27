package bootcamp.model.invoice;

import bootcamp.model.products.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Invoice {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    private int invoiceId;
	private Product product;
	private int count;
    //List<InvoiceItem> items;
    Map<Integer, BigDecimal> mapOfInvoiceIdTotal = new HashMap<>();
    Map<Integer, Integer> mapOfInvoiceIdCount = new HashMap<>();
    Map<Integer, Integer> mapOfInvoiceIdProductId = new HashMap<>();

    public Invoice() {}

    public Invoice(int ID, Product prod, int count, Map<Integer, BigDecimal> mapIdTotal, Map<Integer, Integer> mapIdCount, Map<Integer, Integer> mapIdProdId) {
		this.invoiceId = ID;
		this.product = prod;
		this.count = count;
		this.mapOfInvoiceIdTotal = mapIdTotal;
		this.mapOfInvoiceIdCount = mapIdCount;
		this.mapOfInvoiceIdProductId = mapIdProdId;
	}

	public int getId() {
        return invoiceId;
    }

    public void setId(int ID) {
        this.invoiceId = ID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setInvoiceIdToTotal(int invoiceId, BigDecimal totatCostOfOrder) {
        this.invoiceId = invoiceId;
        mapOfInvoiceIdTotal.put(invoiceId, totatCostOfOrder);
    }

    public BigDecimal getInvocieIdTotal(int invoiceId){
        this.invoiceId = invoiceId;
        return mapOfInvoiceIdTotal.get(invoiceId);
    }

    public void setInvoiceCount(int invoiceId, int availableQuantity) {
        this.invoiceId = invoiceId;
        mapOfInvoiceIdCount.put(invoiceId, availableQuantity);
    }

    public int getInvoiceCount(int invoiceId) {
        this.invoiceId = invoiceId;
        return mapOfInvoiceIdCount.get(invoiceId);
    }

    public void setInvoiceIdProdId(int invoiceId, int prodId) {
        this.invoiceId = invoiceId;
        mapOfInvoiceIdProductId.put(invoiceId, prodId);
    }

    public int getProdId(int invoiceId) {
        this.invoiceId = invoiceId;
        return mapOfInvoiceIdProductId.get(invoiceId);
    }

    //public void setListOfInvoiceItem(List<InvoiceItem> ITEM) { this.items = ITEM;}

    //public List<InvoiceItem> getListOfInvoiceItem() { return items;}

}
