package capstone.services.product.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String requestUrl = response.request().url();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());
        if (responseStatus.is5xxServerError()) {
            return new RestApiServerException(String.format("Encountered error while calling %s", requestUrl), responseStatus);
        } else if (responseStatus.is4xxClientError()) {
            return new RestApiClientException(String.format("Encountered error while calling %s", requestUrl), responseStatus);
        } else {
            return new Exception("Generic exception");
        }
    }
}