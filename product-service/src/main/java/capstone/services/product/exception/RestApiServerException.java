package capstone.services.product.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestApiServerException extends RuntimeException {

    private final HttpStatus status;
    public RestApiServerException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
