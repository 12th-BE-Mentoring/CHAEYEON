package com.example.chaeyeon.domain.school.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherCreateResponse {

    private Long id;
    private String subject;
    private String name;

}
