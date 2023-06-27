package com.example.springbootex.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class NotFoundException extends RuntimeException {
    private final String message;
    private final String userName;
    private final HttpStatus status;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
        this.userName = null;
        this.status = HttpStatus.NOT_FOUND;
    }

    public NotFoundException(String message, String userName) {
        super(message);
        this.message = message;
        this.userName = userName;
        this.status = HttpStatus.NOT_FOUND;
    }
}