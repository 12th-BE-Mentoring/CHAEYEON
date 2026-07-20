package com.example.chaeyeon.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherCreateResponse {

    private Long id;
    private String subject;
    private String name;

}
