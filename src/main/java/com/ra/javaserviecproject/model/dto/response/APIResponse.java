package com.ra.javaserviecproject.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private Object errors;
    private LocalDateTime timestamp;

    public APIResponse(String message, T data, boolean success, int statusCode) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
        this.errors = null;
    }
}