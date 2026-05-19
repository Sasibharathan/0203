package com.MosIC.MosIC_Office.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Catches all unhandled exceptions thrown by any controller
 * and returns a clean, structured JSON error response.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ── 400 Bad Request — @Valid failures ─────────────────────────────────────
    // NEW: without this, validation errors crash with a raw 500.
    // Now they return a clean message like:
    // "gmail: Must be a valid email address, password: Password is required"
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException ex, WebRequest request) {

        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return buildResponse(HttpStatus.BAD_REQUEST, message, request);
    }

    // ── 403 Forbidden ─────────────────────────────────────────────────────────
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleAccessDenied(
            AccessDeniedException ex, WebRequest request) {
        return buildResponse(HttpStatus.FORBIDDEN,
            "You do not have permission to access this resource", request);
    }

    // ── 404 Not Found ─────────────────────────────────────────────────────────
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(
            RuntimeException ex, WebRequest request) {

        String message = ex.getMessage();

        if (message != null && message.toLowerCase().contains("not found")) {
            return buildResponse(HttpStatus.NOT_FOUND, message, request);
        }

        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, request);
    }

    // ── 409 Conflict ──────────────────────────────────────────────────────────
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    // ── Builder ───────────────────────────────────────────────────────────────
    private ResponseEntity<Map<String, Object>> buildResponse(
            HttpStatus status, String message, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status",    status.value());
        body.put("error",     status.getReasonPhrase());
        body.put("message",   message);
        body.put("path",      request.getDescription(false).replace("uri=", ""));

        return ResponseEntity.status(status).body(body);
    }
}