package capstone.services.product.service;

import capstone.services.product.client.CatalogServiceClient;
import capstone.services.product.client.InventoryServiceClient;
import capstone.services.product.exception.ProductNotAvailableException;
import capstone.services.product.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final CatalogServiceClient catalogServiceClient;
    private final InventoryServiceClient inventoryServiceClient;

    public ProductService(CatalogServiceClient catalogServiceClient, InventoryServiceClient inventoryServiceClient) {
        this.catalogServiceClient = catalogServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    public Product getProductByUniqId(String uniqId) {
        Product product = catalogServiceClient.getProductByUniqId(uniqId);
        if (!isProductAvailable(uniqId)) {
            throw new ProductNotAvailableException("Product not available for uniqId: " + uniqId);
        }
        return product;
    }

    public List<Product> getProductsBySku(String sku) {
        List<Product> products = catalogServiceClient.getProductsBySku(sku);
        return getAvailableProducts(products);
    }

    private Boolean isProductAvailable(String uniqId) {
        return inventoryServiceClient.isProductAvailable(uniqId);
    }

    private List<Product> getAvailableProducts(List<Product> products) {
        List<String> uniqueIds = products.stream().map(Product::getUniqId).collect(Collectors.toList());
        Map<String, Boolean> productAvailabilityMap = inventoryServiceClient.getProductAvailabilityList(uniqueIds);
        return products.stream()
                .filter(product -> productAvailabilityMap.getOrDefault(product.getUniqId(), false))
                .collect(Collectors.toList());
    }

}
