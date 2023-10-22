package capstone.services.product.controller;

import capstone.services.product.exception.RestApiServerException;
import capstone.services.product.model.ErrorResponse;
import capstone.services.product.model.Product;
import capstone.services.product.service.ProductService;
import feign.RetryableException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/uniq_id/{uniqId}")
    @CircuitBreaker(name = "productCircuitBreaker", fallbackMethod = "getProductByUniqIdFallback")
    public ResponseEntity<Object> getProductByUniqId(@PathVariable("uniqId") String uniqId) {
        log.info("Entering Product GET /uniq_id/{}", uniqId);
        return ResponseEntity.ok(productService.getProductByUniqId(uniqId));
    }

    public ResponseEntity<Object> getProductByUniqIdFallback(String uniqId, Exception exception) throws Exception {
        if (exception instanceof RetryableException) {
            log.error("CircuitBreaker fallback method: RetryableException. {}", exception.getMessage());
            ErrorResponse errorResponse = new ErrorResponse("RETRYABLE_EXCEPTION", exception.getMessage());
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(errorResponse);
        }
        if (exception instanceof RestApiServerException) {
            log.error("CircuitBreaker fallback method: RestApiServerException. {}", exception.getMessage());
            ErrorResponse errorResponse = new ErrorResponse("REST_API_SERVER_EXCEPTION", exception.getMessage());
            return ResponseEntity.status(((RestApiServerException) exception).getStatus()).body(errorResponse);
        }
        if (exception instanceof CallNotPermittedException) {
            log.error("CircuitBreaker fallback method: CallNotPermittedException. {}", exception.getMessage());
            ErrorResponse errorResponse = new ErrorResponse("CALL_NOT_PERMITTED", exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
        throw exception;
    }

    @GetMapping("/sku/{sku}")
    public List<Product> getProductsBySku(@PathVariable("sku") String sku) {
        log.info("Entering Product GET /sku/{}", sku);
        return productService.getProductsBySku(sku);
    }

}
