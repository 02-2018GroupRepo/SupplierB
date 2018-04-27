package bootcamp.controller;

import bootcamp.model.inventory.InventoryItem;
import bootcamp.model.products.Product;
import bootcamp.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("inventory/all")
	public List<InventoryItem> showInventory(){
		return inventoryService.getInventory();
	}

	@GetMapping("/inventory/{id}")
	public InventoryItem showInventoryById(@PathVariable Integer id){
		log.debug("Retreiving product " + id);
		return inventoryService.getInventoryById(id);
	}

	@RequestMapping(name = "inventory/receive", method=RequestMethod.POST)
    public void getProduct(@RequestBody List<Product> products) {
		log.debug("Receiving products");
    	inventoryService.receiveInventory(products); 
    }
	
}
