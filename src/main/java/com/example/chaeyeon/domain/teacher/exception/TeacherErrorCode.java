package com.example.chaeyeon.domain.teacher.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TeacherErrorCode {

    TEACHER_NOT_FOUND(HttpStatus.NOT_FOUND, "선생님을 찾을 수 없습니다.");

    private final HttpStatus Status;
    private final String message;
}
