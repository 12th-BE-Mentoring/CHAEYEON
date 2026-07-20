package com.example.chaeyeon.presentation.dto.request;

import lombok.Getter;

@Getter
public class StudentCreateRequest {


    private String name;

    private int grade;
    private int classNumber;
    private int studentNumber;

    private String phone;

    private Long teacherId; // Teacher 객체 전체가 아닌 ID만 받는다
}
