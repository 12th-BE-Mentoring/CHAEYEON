package com.example.chaeyeon.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentCreateResponse {

    private Long id;
    private String name;
    private int grade;
    private int classNumber;
    private int studentNumber;
    private Long homeroomTeacherId;
}
