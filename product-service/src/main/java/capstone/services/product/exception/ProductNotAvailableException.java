package capstone.services.product.exception;

public class ProductNotAvailableException extends RuntimeException {
    public ProductNotAvailableException(String message) {
        super(message);
    }
}
