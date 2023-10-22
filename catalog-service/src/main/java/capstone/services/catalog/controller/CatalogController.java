package capstone.services.catalog.controller;

import capstone.services.catalog.model.Product;
import capstone.services.catalog.service.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CatalogController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogController.class);


	private final CatalogService catalogService;

	@Autowired
	public CatalogController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	@GetMapping("/{uniqId}")
	public Product getProductByUniqId(@PathVariable String uniqId) throws InterruptedException {
		log.info("Entering Catalog GET /{}", uniqId);
		return catalogService.findByUniqId(uniqId);
	}

	@GetMapping("/sku/{sku}")
	public List<Product> getProductsBySku(@PathVariable String sku) throws InterruptedException {
		log.info("Entering Catalog GET /sku/{}", sku);
		// Simulate delay
//		Thread.sleep(3000);
		return catalogService.findBySku(sku);
	}

	@GetMapping("/")
	public List<Product> getAllProducts() {
		return catalogService.getAllProducts();
	}
	
}
