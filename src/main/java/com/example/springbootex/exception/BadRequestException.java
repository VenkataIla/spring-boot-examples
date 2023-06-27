package com.example.springbootex.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class BadRequestException extends RuntimeException {
    private final String message;
    private final String responseBody;
    private final HttpStatus status;

    public BadRequestException(String message, String errorResponseBody) {
        super(message);
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST;
        this.responseBody = errorResponseBody;
    }

    public BadRequestException(String message) {
        super(message);
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST;
        this.responseBody = message;
    }

}