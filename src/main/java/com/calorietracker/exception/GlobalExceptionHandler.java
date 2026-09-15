package com.calorietracker.exception;

import com.calorietracker.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClaudeApiException.class)
    public ResponseEntity<ApiErrorResponse> handleClaudeApiException(
            ClaudeApiException exception
    ) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                "Claude API request failed",
                exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(errorResponse);
    }

    @ExceptionHandler(AiResponseParseException.class)
    public ResponseEntity<ApiErrorResponse> handleAiResponseParseException(
            AiResponseParseException exception
    ) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                "AI response parsing failed",
                exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(errorResponse);
    }
}