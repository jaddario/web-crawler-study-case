package com.addario.webcrawlerapi.exceptions;

public class URLException extends RuntimeException {
    public URLException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }
}
