package capstone.services.product.client;

import capstone.services.product.exception.MyFeignClientConfiguration;
import capstone.services.product.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "catalog-service", configuration = MyFeignClientConfiguration.class)
public interface CatalogServiceClient {

    @GetMapping("/{uniqId}")
    Product getProductByUniqId(@PathVariable("uniqId") String uniqId);

    @GetMapping("/sku/{sku}")
    List<Product> getProductsBySku(@PathVariable("sku") String sku);

}
