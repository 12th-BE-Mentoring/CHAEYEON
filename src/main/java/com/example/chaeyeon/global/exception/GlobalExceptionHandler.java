package com.example.chaeyeon.global.exception;

import com.example.chaeyeon.domain.teacher.exception.TeacherErrorCode;
import com.example.chaeyeon.domain.teacher.exception.TeacherException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TeacherException.class)
    public ResponseEntity<ErrorResponse> teacherException(TeacherException e) {

        TeacherErrorCode errorCode = e.getErrorCode();

        ErrorResponse response = ErrorResponse.builder()
                .message(errorCode.getMessage())
                .code(errorCode.name())
                .build();

        return ResponseEntity.status(errorCode.getStatus())
                .body(response);

    }
}
