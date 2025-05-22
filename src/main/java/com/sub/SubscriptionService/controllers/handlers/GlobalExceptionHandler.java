package com.sub.SubscriptionService.controllers.handlers;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

record MessageInfo(String field, String message){}

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {

        List<MessageInfo> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new MessageInfo(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        return buildErrorResponse(HttpStatus.BAD_REQUEST, errors, request);
    }

    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class})
    public ResponseEntity<Object> handleBadRequestExceptions(
            Exception ex, WebRequest request) {

        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<Object> handleResponseStatusExceptions(
            Exception ex, WebRequest request) {

        ResponseStatusException responseStatusException = (ResponseStatusException) ex;
        HttpStatus status = HttpStatus.valueOf(responseStatusException.getStatusCode().value());
        return buildErrorResponse(status, responseStatusException.getReason(), request);
    }

    private ResponseEntity<Object> buildErrorResponse(
            HttpStatus status, Object details, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("path", request.getDescription(false).replace("uri=", ""));
        body.put("details", details);

        return new ResponseEntity<>(body, status);
    }
}
