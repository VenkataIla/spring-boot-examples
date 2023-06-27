package com.example.springbootex.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class UnauthorizedException extends RuntimeException {
    private final String message;
    private final String userName;
    private final HttpStatus status;

    public UnauthorizedException(String message) {
        super(message);
        this.message = message;
        this.status = HttpStatus.UNAUTHORIZED;
        this.userName = null;
    }

    public UnauthorizedException(String message, String userName) {
        super(message);
        this.message = message;
        this.userName = userName;
        this.status = HttpStatus.UNAUTHORIZED;
    }
}
