package com.example.springbootex.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class InternalServerException extends RuntimeException {

    private final String message;
    private final HttpStatus status;

    public InternalServerException(String message) {
        super(message);
        this.message = message;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public InternalServerException(HttpStatus status, String message) {
        super(message);
        this.message = message;
        this.status = status;
    }

}
