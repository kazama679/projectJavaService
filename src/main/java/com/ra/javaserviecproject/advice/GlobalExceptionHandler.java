package com.ra.javaserviecproject.advice;

import com.ra.javaserviecproject.model.dto.response.APIResponse;
import com.ra.javaserviecproject.model.dto.response.ValidationError;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<APIResponse<?>> handleExpiredJwt(ExpiredJwtException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(APIResponse.builder()
                        .success(false)
                        .message("Token đã hết hạn")
                        .errors(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<APIResponse<?>> handleMalformedJwt(MalformedJwtException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(APIResponse.builder()
                        .success(false)
                        .message("Token không hợp lệ")
                        .errors(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<APIResponse<?>> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(APIResponse.builder()
                        .success(false)
                        .message("Bạn không có quyền truy cập")
                        .errors(request.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<?>> handleGenericException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(APIResponse.builder()
                        .success(false)
                        .message("Đã xảy ra lỗi hệ thống")
                        .errors(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ValidationError> validationErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ValidationError(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        APIResponse<Void> response = APIResponse.<Void>builder()
                .success(false)
                .message("Dữ liệu không hợp lệ")
                .data(null)
                .errors(validationErrors)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<APIResponse<?>> handleInvalidFormat(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException invalidFormat) {
            String fieldName = "";
            if (!invalidFormat.getPath().isEmpty()) {
                fieldName = invalidFormat.getPath().get(0).getFieldName();
            }
            ValidationError error = new ValidationError(
                    fieldName,
                    "Định dạng không hợp lệ, vui lòng nhập đúng định dạng"
            );
            APIResponse<?> response = APIResponse.builder()
                    .success(false)
                    .message("Dữ liệu không hợp lệ")
                    .errors(List.of(error))
                    .timestamp(LocalDateTime.now())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(APIResponse.builder()
                        .success(false)
                        .message("Dữ liệu gửi lên không hợp lệ")
                        .errors(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}