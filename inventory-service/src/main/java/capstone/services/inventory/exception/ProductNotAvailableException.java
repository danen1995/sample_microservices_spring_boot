package capstone.services.inventory.exception;

public class ProductNotAvailableException extends RuntimeException {
    public ProductNotAvailableException(String message) {
        super(message);
    }
}
