package com.example.springbootex.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class ConnectionException extends RuntimeException {
    private final String message;
    private final String responseBody;
    private final HttpStatus status;

    public ConnectionException(String message, String errorResponseBody) {
        super(message);
        this.message = message;
        this.status = HttpStatus.NOT_EXTENDED;
        this.responseBody = errorResponseBody;
    }

    public ConnectionException(String message) {
        super(message);
        this.message = message;
        this.status = HttpStatus.NOT_EXTENDED;
        this.responseBody = message;
    }

}