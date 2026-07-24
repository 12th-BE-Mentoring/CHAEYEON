package com.example.chaeyeon.domain.teacher.exception;

import lombok.Getter;

@Getter
public class TeacherException extends RuntimeException {

    private TeacherErrorCode errorCode;

    public TeacherException(TeacherErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
