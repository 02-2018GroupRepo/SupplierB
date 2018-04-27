package bootcamp.dao;

import bootcamp.model.inventory.Inventory;
import bootcamp.model.inventory.InventoryItem;
import bootcamp.model.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;

@Component
public class InventoryDao {

    private final String GET_INVENTORY_ALL = "SELECT product.id, product.retail_price, inventory.number_available FROM product INNER JOIN inventory ON inventory.id = product.id";
    private final String Add_Inventory = "INSERT INTO product values(?,?,?,?,?)";
    private final String GET_INVENTORY = "SELECT product.id, product.retail_price, inventory.number_available FROM product INNER JOIN inventory ON inventory.id = product.id";
    private final String GET_INVENTORY_BY_ID_SQL = GET_INVENTORY + " where inventory.id = ?";

    private final String EXISTING_INVENTORY = "SELECT * from inventory";
    private final String UPDATE_QUANTITY = "UPDATE inventory SET number_available=? WHERE id=?";
    private final String UPDATE_WHOLESALE_RETAIL_PRICE = "UPDATE product SET wholesale_price=?, retail_price=? WHERE id=?";



//    Object[] params = {100, "MAYUR", "Hello World", 12.5, 5.5};
//    int [] types = {Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.DOUBLE, Types.DOUBLE};

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;

    @Autowired
    private List<Product> inventoryList;

    public void receiveInventory (List<Product> products) {

        inventoryList.addAll(products);
        List<Inventory> existingProduct = jdbcTemplate.query(EXISTING_INVENTORY, new BeanPropertyRowMapper<>(Inventory.class));

        Object[] params;
        int[] types;

        Object[] params1;
        int [] types1;

        for (Product prod : products) {

            boolean found = false;

            for (Inventory inven : existingProduct) {

                if(inven.getId() == prod.getId()) {

                    found = true;

                    int quantity = inven.getNumber_available() + 1;
                    BigDecimal wholesalePrice = prod.getRetail_price();
                    BigDecimal increaseByPercent = new BigDecimal(0.30);

                    BigDecimal updateRetailPrice = wholesalePrice.multiply(increaseByPercent);
                    updateRetailPrice = updateRetailPrice.add(wholesalePrice);

                    params = new Object[]{quantity};
                    types = new int [] {Types.INTEGER};
                    //jdbcTemplate.update("UPDATE inventory SET number_available=? WHERE id= " + inven.getId(), params, types);
                    jdbcTemplate.update("UPDATE inventory SET number_available= " + quantity  + " WHERE id= " + inven.getId());

                    params1 = new Object[] {wholesalePrice, updateRetailPrice};
                    types1 = new int[] {Types.DECIMAL, Types.DECIMAL};
                    jdbcTemplate.update("UPDATE product SET wholesale_price=?, retail_price=? WHERE id= " + prod.getId(), params1, types1);

                }
               // else {

//                    jdbcTemplate.update("INSERT INTO inventory values( " + prod.getId() + ", " + 1 + " )" );
//
//                    BigDecimal wholesalePrice = prod.getRetail_price();
//                    BigDecimal increaseByPercent = new BigDecimal(0.30);
//
//                    BigDecimal updateRetailPrice = wholesalePrice.multiply(increaseByPercent);
//                    updateRetailPrice = updateRetailPrice.add(wholesalePrice);
//
//                    params1 = new Object[] {wholesalePrice, updateRetailPrice};
//                    types1 = new int[] {Types.DECIMAL, Types.DECIMAL};
//                    jdbcTemplate.update("UPDATE product SET wholesale_price=?, retail_price=? WHERE id= " + prod.getId(), params1, types1);

                //}
            }
            if(!found) {

                jdbcTemplate.update("INSERT INTO inventory values( " + prod.getId() + ", " + 1 + " )" );

                BigDecimal wholesalePrice = prod.getRetail_price();
                BigDecimal increaseByPercent = new BigDecimal(0.30);

                BigDecimal updateRetailPrice = wholesalePrice.multiply(increaseByPercent);
                updateRetailPrice = updateRetailPrice.add(wholesalePrice);

                params1 = new Object[] {wholesalePrice, updateRetailPrice};
                types1 = new int[] {Types.DECIMAL, Types.DECIMAL};
                jdbcTemplate.update("UPDATE product SET wholesale_price=?, retail_price=? WHERE id= " + prod.getId(), params1, types1);

            }

//            params = new Object[]{prod.getId(), prod.getName(), prod.getDescription(), prod.getRetail_price(), prod.getWholesale_price()};
//            types = new int[]{Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.DECIMAL, Types.DECIMAL};
//            jdbcTemplate.update(Add_Inventory, params, types);
//
//            System.out.println(prod);
        }
    }

    public List<InventoryItem> getInventory() {
        //return inventoryList;
        return jdbcTemplate.query(GET_INVENTORY_ALL, new BeanPropertyRowMapper<>(InventoryItem.class));
    }

    public InventoryItem getInventoryById(Integer id) {
        return jdbcTemplate.queryForObject(GET_INVENTORY_BY_ID_SQL, new Object[] {id}, new BeanPropertyRowMapper<>(InventoryItem.class));
    }

    public List<Product> getInventoryList() {
        return inventoryList;
    }

    @PostConstruct
    public void initDataSource() {
        jdbcTemplate = new JdbcTemplate((javax.sql.DataSource) dataSource);
    }
}
