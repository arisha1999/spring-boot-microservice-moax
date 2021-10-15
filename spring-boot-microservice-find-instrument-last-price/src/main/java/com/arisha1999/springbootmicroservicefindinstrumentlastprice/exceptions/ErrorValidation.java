package com.arisha1999.springbootmicroservicefindinstrumentlastprice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ErrorValidation extends SubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ErrorValidation(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
