package org.example.trading.api.exception;

@RestControllerAdvice
public class TradingApiExceptionHandler extends ResponseEntityExceptionHandler {

    // 200 OK - Successful GET, PUT, PATCH
    // 201 Created - Successful POST
    // 204 No Content - Successful DELETE
    // 400 Bad Request - Client error (validation failure)
    // 401 Unauthorized - Authentication required
    // 403 Forbidden - Insufficient permissions
    // 404 Not Found - Resource doesn't exist
    // 409 Conflict - Business rule violation
    // 422 Unprocessable Entity - Semantic error
    // 429 Too Many Requests - Rate limit exceeded
    // 500 Internal Server Error - Server error
    // 503 Service Unavailable - Temporary outage

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Resource Not Found",
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientFunds(InsufficientFundsException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Insufficient Funds",
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidation(ValidationException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MarketClosedException.class)
    public ResponseEntity<ErrorResponse> handleMarketClosed(MarketClosedException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Market Closed",
                "Trading is not available. Market hours: 9:30 AM - 4:00 PM ET",
                System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(RateLimitExceededException.class)
    public ResponseEntity<ErrorResponse> handleRateLimit(RateLimitExceededException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.TOO_MANY_REQUESTS.value(),
                "Rate Limit Exceeded",
                ex.getMessage(),
                System.currentTimeMillis()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RateLimit-Limit", "100");
        headers.add("X-RateLimit-Remaining", "0");
        headers.add("X-RateLimit-Reset", String.valueOf(ex.getResetTime()));

        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .headers(headers)
                .body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericError(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "An unexpected error occurred. Please try again later.",
                System.currentTimeMillis()
        );
        // Log the actual exception for debugging
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

// Error response model
@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String error;
    private String message;
    private long timestamp;
}
