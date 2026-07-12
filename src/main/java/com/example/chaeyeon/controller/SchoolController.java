package com.example.chaeyeon.controller;

import com.example.chaeyeon.domain.Student;
import com.example.chaeyeon.domain.Teacher;
import com.example.chaeyeon.dto.request.StudentCreateRequest;
import com.example.chaeyeon.dto.request.TeacherCreateRequest;
import com.example.chaeyeon.dto.response.StudentCreateResponse;
import com.example.chaeyeon.dto.response.TeacherCreateResponse;
import com.example.chaeyeon.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED) /*해당 요청이 끝났을 때 프론트에 보낼 상태코드를 강제한다.*/
    public StudentCreateResponse CreateStudent(@RequestBody StudentCreateRequest request){

        Student student = schoolService.createStudent(request);

        return StudentCreateResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .grade(student.getGrade())
                .classNumber(student.getClassNumber())
                .studentNumber(student.getStudentNumber())
                .homeroomTeacherId(student.getHomeroomTeacher() == null
                        ? null : student.getHomeroomTeacher().getId())
                .build();

    }

    @PostMapping("/teacher")
    @ResponseStatus(HttpStatus.CREATED) // 해당 요청이 끝났을 때 프론트에 보낼 상태코드 강제
    public TeacherCreateResponse CreateTeacher(@RequestBody TeacherCreateRequest request){

        Teacher teacher = schoolService.createTeacher(request);

        return TeacherCreateResponse.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .subject(teacher.getSubject())
                .build();
    }
}
