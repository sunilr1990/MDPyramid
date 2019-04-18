package com.casestudy.pyramid.exception;

public class PyramidException extends RuntimeException {

    private static final long serialVersionUID = -1;

    public PyramidException(final String message) {
        super(message);
    }

    public PyramidException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}
