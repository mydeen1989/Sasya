package com.sasya.exception;

import com.sasya.response.SasyaResponse;
import org.springframework.http.HttpStatus;

/**
 * SasyaException
 */
public class SasyaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final HttpStatus httpStatus;

    public SasyaException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static SasyaException build(String message, HttpStatus httpStatus){
        return new SasyaException(message,httpStatus);
    }

}
