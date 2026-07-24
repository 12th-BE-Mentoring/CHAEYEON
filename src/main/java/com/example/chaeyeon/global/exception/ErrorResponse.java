package com.example.chaeyeon.global.exception;

import lombok.Builder;

@Builder
public class ErrorResponse {

    private String message;
    private String code;
}
