package capstone.services.inventory.service;

import capstone.services.inventory.exception.ProductNotAvailableException;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class InventoryService {

    private final Map<String, Boolean> productAvailabilityMap;

    public InventoryService() {
        productAvailabilityMap = new HashMap<>();
    }

    public boolean isProductAvailable(String uniqId) {
        Boolean aBoolean = productAvailabilityMap.get(uniqId);
        if (aBoolean == null) {
            throw new ProductNotAvailableException("Product not available for uniqId: " + uniqId);
        }
        return aBoolean;
    }

    @PostConstruct
    private void loadDataFromCsv() {
        Random random = new Random();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jcpenney_com-ecommerce_sample.csv");
             Reader reader = new InputStreamReader(inputStream);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord record : csvParser) {
                String uniqId = record.get("uniq_id");
                boolean isAvailable = random.nextBoolean();
                productAvailabilityMap.put(uniqId, isAvailable);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem while loading the data from CSV file.");
        }
    }
}
