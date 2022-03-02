package com.zipbom.zipbom.Global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) {
        ErrorResponse response = new ErrorResponse(e.getErrorCode(),e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoticeEntityNotFoundException.class)
    public ResponseEntity<?> handleNoticeException(NoticeEntityNotFoundException e) {
        ErrorResponse response = new ErrorResponse(e.getErrorCode(),e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
