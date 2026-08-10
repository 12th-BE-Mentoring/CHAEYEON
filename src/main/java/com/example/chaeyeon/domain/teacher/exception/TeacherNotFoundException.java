package com.example.chaeyeon.domain.teacher.exception;

import com.example.chaeyeon.global.exception.BusinessException;
import com.example.chaeyeon.global.exception.ErrorCode;

public class TeacherNotFoundException extends BusinessException {
    public static final TeacherNotFoundException EXCEPTION = new TeacherNotFoundException();
    public TeacherNotFoundException() {
        super(ErrorCode.TEACHER_NOT_FOUND);
    }
}
