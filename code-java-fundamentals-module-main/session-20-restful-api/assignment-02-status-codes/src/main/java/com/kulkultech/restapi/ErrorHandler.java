/**
 * Global Exception Handler - HTTP Status Codes and Error Handling
 * 
 * Challenge: Implement global exception handling with proper status codes
 * 
 * Your task: Complete this exception handler class
 * 
 * Concepts covered:
 * - @RestControllerAdvice for global exception handling
 * - @ExceptionHandler for specific exceptions
 * - @ResponseStatus for HTTP status codes
 * - Standardized error responses
 */

package com.kulkultech.restapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// TODO: Add @RestControllerAdvice annotation
// This makes this class handle exceptions globally

public class ErrorHandler {
    
    // TODO: Add @ExceptionHandler(PortfolioNotFoundException.class)
    // TODO: Add @ResponseStatus(HttpStatus.NOT_FOUND)
    // This method should handle PortfolioNotFoundException
    public ErrorResponse handlePortfolioNotFound(/* TODO: Add PortfolioNotFoundException parameter */) {
        // TODO: Create ErrorResponse with status 404 and exception message
        // TODO: Return the ErrorResponse
        return null;
    }
    
    // TODO: Add @ExceptionHandler(Exception.class) for generic exceptions
    // TODO: Add @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // This method should handle all other exceptions
    public ErrorResponse handleGenericException(/* TODO: Add Exception parameter */) {
        // TODO: Create ErrorResponse with status 500 and generic message
        // TODO: Return the ErrorResponse
        return null;
    }
}

