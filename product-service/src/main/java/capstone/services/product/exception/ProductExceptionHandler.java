package capstone.services.product.exception;

import capstone.services.product.controller.ProductController;
import capstone.services.product.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = ProductController.class)
public class ProductExceptionHandler {

    @ExceptionHandler(RestApiClientException.class)
    public ResponseEntity<Object> handleException(RestApiClientException ex) {
        ErrorResponse errorResponse = new ErrorResponse("REST_API_CLIENT_EXCEPTION", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(ProductNotAvailableException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotAvailableException ex) {
        ErrorResponse errorResponse = new ErrorResponse("PRODUCT_NOT_AVAILABLE", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
