package capstone.services.product.client;

import capstone.services.product.exception.MyFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "inventory-service", configuration = MyFeignClientConfiguration.class)
public interface InventoryServiceClient {

    @GetMapping("/availability/{uniqId}")
    Boolean isProductAvailable(@PathVariable("uniqId") String uniqId);

    @PostMapping("/availability")
    Map<String, Boolean> getProductAvailabilityList(@RequestBody List<String> uniqIds);

}
