package com.example.util.rest

import javax.servlet.http.HttpServletRequest
import java.time.LocalDateTime

/**
 * Custom Error Object for returning errors the client
 */
class ApiError {
    LocalDateTime timeStamp = LocalDateTime.now();
    String url
    String message

    ApiError() {}

    ApiError(HttpServletRequest req) {
        url = req.getRequestURI()
    }

    ApiError(HttpServletRequest req, Exception e) {
        url = req.getRequestURI()
        message = e.message
    }
}
