package com.example.springbootex.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class ForbiddenException extends RuntimeException {
    private String message;
    private String userName;
    private Boolean poNumberCurrentAvailable;
    private HttpStatus status;

    public ForbiddenException(String message) {
        super(message);
        this.message = message;
        this.status = HttpStatus.FORBIDDEN;
    }

    public ForbiddenException(String message, String userName) {
        super(message);
        this.message = message;
        this.userName = userName;
        this.status = HttpStatus.FORBIDDEN;
    }

    public ForbiddenException(String message, Boolean poNumberCurrentAvailable) {
        super(message);
        this.message = message;
        this.poNumberCurrentAvailable = poNumberCurrentAvailable;
        this.status = HttpStatus.FORBIDDEN;
    }
}
