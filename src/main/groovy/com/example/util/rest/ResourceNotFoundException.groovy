package com.example.util.rest

/**
 * Simple class to indicate that we didn't find something we were looking for
 */
class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message)
    }
}