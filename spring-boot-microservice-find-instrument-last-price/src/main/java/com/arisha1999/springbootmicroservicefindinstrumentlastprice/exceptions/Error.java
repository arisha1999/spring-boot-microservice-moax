package com.arisha1999.springbootmicroservicefindinstrumentlastprice.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class Error {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<SubError> subErrors;

    private Error() {
        timestamp = LocalDateTime.now();
    }

    Error(HttpStatus status) {
        this();
        this.status = status;
    }

    Error(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    Error(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}
