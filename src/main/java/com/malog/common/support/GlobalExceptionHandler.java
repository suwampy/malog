package com.malog.common.support;

import com.malog.common.error.ErrorCode;
import com.malog.common.error.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> onError(ResourceNotFoundException exception) {
        log.debug("ResourceNotFoundException: {}", exception.getMessage());
        return ResponseEntity.status(exception.getErrorCode().getStatus())
            .body(exception.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> onError(HttpRequestMethodNotSupportedException exception) {
        log.debug("HttpRequestMethodNotSupportedException: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
            .body(ErrorCode.METHOD_NOT_ALLOWED.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> onError(HttpMediaTypeNotSupportedException exception) {
        log.debug("HttpMediaTypeNotSupportedException: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
            .body(ErrorCode.UNSUPPORTED_MEDIA_TYPE.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> onError(MethodArgumentNotValidException exception) {
        log.error("MethodArgumentNotValidException: {}", exception.getBindingResult());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorCode.INVALID_INPUT.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> onError(BindException exception) {
        log.error("MethodArgumentNotValidException: {}", exception.getBindingResult());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorCode.INVALID_INPUT);
    }
}
