package capstone.services.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private final String errorCode;
    private final String errorMessage;
}
