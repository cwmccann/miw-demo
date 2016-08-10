package com.example.util.rest

/**
 * Simple class to indicate that we didn't find something we were looking for
 */
class BusinessException extends RuntimeException {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message)
    }
}