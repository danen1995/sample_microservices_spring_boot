package capstone.services.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Product {
    private String uniqId;
    private String sku;
    private String nameTitle;
    private String description;
    private String listPrice;
    private String salePrice;
    private String category;
    private String categoryTree;
    private String averageProductRating;
    private String productUrl;
    private String productImageUrls;
    private String brand;
    private String totalNumberReviews;


}
