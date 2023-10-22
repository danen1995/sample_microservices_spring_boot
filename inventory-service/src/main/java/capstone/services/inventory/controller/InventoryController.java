package capstone.services.inventory.controller;

import capstone.services.inventory.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // ovo ne treba
    @GetMapping("/availability/{uniqId}")
    public Boolean getProductAvailability(@PathVariable String uniqId) throws InterruptedException {
        log.info("Entering Inventory GET /availability/{}", uniqId);
        // Simulate delay
        Thread.sleep(5000);
        return inventoryService.isProductAvailable(uniqId);
    }

    @PostMapping("/availability")
    public Map<String, Boolean> getProductAvailabilityList(@RequestBody List<String> uniqIds) {
        log.info("Entering Inventory GET /availability");
        return uniqIds.stream().collect(Collectors.toMap(uniqId -> uniqId, inventoryService::isProductAvailable));
    }

}
