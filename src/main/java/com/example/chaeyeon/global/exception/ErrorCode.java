package com.example.chaeyeon.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_CREDENTIALS(HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호가 일치하지 않습니다."),

    TEACHER_NOT_FOUND(HttpStatus.NOT_FOUND, "선생님을 찾을 수 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),

    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "유효하지 않은 요청입니다."),

    USERNMAE_ALREDY_EXISTS(HttpStatus.CONFLICT,"이미 존재하는 사용자 이름 입니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");

    private final HttpStatus Status;
    private final String message;
}
