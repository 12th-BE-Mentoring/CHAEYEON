package com.example.chaeyeon.domain.school.application;

import com.example.chaeyeon.domain.student.domain.Student;
import com.example.chaeyeon.domain.teacher.domain.Teacher;
import com.example.chaeyeon.domain.school.presentation.dto.request.StudentCreateRequest;
import com.example.chaeyeon.domain.school.presentation.dto.request.TeacherCreateRequest;
import com.example.chaeyeon.domain.school.presentation.dto.response.StudentCreateResponse;
import com.example.chaeyeon.domain.school.presentation.dto.response.TeacherCreateResponse;
import com.example.chaeyeon.domain.student.domain.StudentRepository;
import com.example.chaeyeon.domain.teacher.domain.TeacherRepository;
import com.example.chaeyeon.domain.teacher.exception.TeacherErrorCode;
import com.example.chaeyeon.domain.teacher.exception.TeacherException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SchoolService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public StudentCreateResponse createStudent(StudentCreateRequest request){
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new TeacherException(TeacherErrorCode.TEACHER_NOT_FOUND));

        Student student = new Student(request.getName(), request.getGrade(), request.getClassNumber()
        , request.getStudentNumber(), request.getPhone());

        teacher.addStudent(student);

        studentRepository.save(student);

        StudentCreateResponse response = StudentCreateResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .grade(student.getGrade())
                .classNumber(student.getClassNumber())
                .studentNumber(student.getStudentNumber())
                .homeroomTeacherId(teacher.getId())
                .build();

        return response;
    }

    public TeacherCreateResponse createTeacher(TeacherCreateRequest request){
        Teacher teacher = new Teacher(request.getName(), request.getSubject(),
                request.getPhone());

        teacherRepository.save(teacher);

        TeacherCreateResponse response = TeacherCreateResponse.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .subject(teacher.getSubject())
                .build();

        return response;
    }
}
