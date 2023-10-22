package capstone.services.catalog.service;

import capstone.services.catalog.exception.ProductNotFoundException;
import capstone.services.catalog.model.Product;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogService {

	private final List<Product> products;

	private CatalogService() {
		products = new ArrayList<>();
	}

	public Product findByUniqId(String uniqId) {
		return products.stream()
				.filter(product -> uniqId.equals(product.getUniqId()))
				.findFirst()
				.orElseThrow(() -> new ProductNotFoundException("Product not found for uniqId: " + uniqId));
	}

	public List<Product> findBySku(String sku) {
		return products.stream()
				.filter(product -> sku.equals(product.getSku()))
				.collect(Collectors.toList());
	}

	public List<Product> getAllProducts() {
		return products;
	}

	@PostConstruct
	private void loadDataFromCsv() {
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jcpenney_com-ecommerce_sample.csv");
			 Reader reader = new InputStreamReader(inputStream);
			 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

			for (CSVRecord record : csvParser) {
				String uniqId = record.get("uniq_id");
				String sku = record.get("sku");
				String nameTitle = record.get("name_title");
				String description = record.get("description");
				String listPrice = record.get("list_price");
				String salePrice = record.get("sale_price");
				String category = record.get("category");
				String categoryTree = record.get("category_tree");
				String averageProductRating =record.get("average_product_rating");
				String productUrl = record.get("product_url");
				String productImageUrls = record.get("product_image_urls");
				String brand = record.get("brand");
				String totalNumberReviews = record.get("total_number_reviews");

				Product product = Product.builder()
						.uniqId(uniqId)
						.sku(sku)
						.nameTitle(nameTitle)
						.description(description)
						.listPrice(listPrice)
						.salePrice(salePrice)
						.category(category)
						.categoryTree(categoryTree)
						.averageProductRating(averageProductRating)
						.productUrl(productUrl)
						.productImageUrls(productImageUrls)
						.brand(brand)
						.totalNumberReviews(totalNumberReviews)
						.build();

				products.add(product);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Problem while loading the data from CSV file.");
		}

	}
}
