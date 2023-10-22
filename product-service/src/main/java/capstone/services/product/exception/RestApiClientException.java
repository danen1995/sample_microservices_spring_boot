package capstone.services.product.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestApiClientException extends RuntimeException {

    private final HttpStatus status;
    public RestApiClientException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
