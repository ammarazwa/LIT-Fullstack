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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(PortfolioNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlePortfolioNotFound(PortfolioNotFoundException ex) {
        return new ErrorResponse(404, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericException(Exception ex) {
        return new ErrorResponse(500, "An error occurred: " + ex.getMessage());
    }
}

