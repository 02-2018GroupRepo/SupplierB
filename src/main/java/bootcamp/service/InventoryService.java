package bootcamp.service;

import bootcamp.dao.InventoryDao;
import bootcamp.model.inventory.InventoryItem;
import bootcamp.model.products.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class InventoryService {
	
	@Autowired
	//private List<Product> inventoryList;
	private static final Logger log = LoggerFactory.getLogger(InventoryService.class);
	 
	 @Autowired
	 private SimpleDateFormat dateFormat;

	 @Autowired
	 private InventoryDao invenDao;
	
	public void receiveInventory(List<Product> products) {
	   invenDao.receiveInventory(products);
	    //inventoryList.addAll(products);
	}

	public List<InventoryItem> getInventory(){
		return invenDao.getInventory();
	    //return inventoryList;
	}

    public InventoryItem getInventoryById(Integer id) {
	    return invenDao.getInventoryById(id);
    }

    public List<Product> getInventoryList() {
        return invenDao.getInventoryList();
    }
	
	@Scheduled(cron = "${inventory.status.schedule}")
    public void inventoryStatus() {
        log.info("Checking on inventory status at {}", dateFormat.format(new Date()));
        log.debug("Debug goes here");
    }
}
