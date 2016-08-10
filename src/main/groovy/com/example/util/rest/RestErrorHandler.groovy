package com.example.util.rest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest

/**
 * Handles formatting errors from the client
 */
@ControllerAdvice
@RestController
class RestErrorHandler {

    @ExceptionHandler(value = ResourceNotFoundException)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError processNotFoundException(HttpServletRequest req, ResourceNotFoundException ex) {
        def apiError = new ApiError(req, ex)
        return apiError
    }

    @ExceptionHandler(value = Exception)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError processNotFoundException(HttpServletRequest req, Exception ex) {
        def apiError = new ApiError(req, ex)
        return apiError
    }
}
