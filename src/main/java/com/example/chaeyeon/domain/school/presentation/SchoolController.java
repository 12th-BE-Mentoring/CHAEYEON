package com.example.chaeyeon.domain.school.presentation;

import com.example.chaeyeon.domain.school.presentation.dto.request.StudentCreateRequest;
import com.example.chaeyeon.domain.school.presentation.dto.request.TeacherCreateRequest;
import com.example.chaeyeon.domain.school.presentation.dto.response.StudentCreateResponse;
import com.example.chaeyeon.domain.school.presentation.dto.response.TeacherCreateResponse;
import com.example.chaeyeon.domain.school.application.SchoolService;
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
    public StudentCreateResponse createStudent(@RequestBody StudentCreateRequest request){

        return schoolService.createStudent(request);
    }

    @PostMapping("/teachers")
    @ResponseStatus(HttpStatus.CREATED) // 해당 요청이 끝났을 때 프론트에 보낼 상태코드 강제
    public TeacherCreateResponse createTeacher(@RequestBody TeacherCreateRequest request){

        return schoolService.createTeacher(request);
    }
}
