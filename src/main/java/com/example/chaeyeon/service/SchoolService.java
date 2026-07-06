package com.example.chaeyeon.service;

import com.example.chaeyeon.domain.Student;
import com.example.chaeyeon.domain.Teacher;
import com.example.chaeyeon.dto.StudentCreateRequest;
import com.example.chaeyeon.dto.TeacherCreateRequest;
import com.example.chaeyeon.repository.StudentRepository;
import com.example.chaeyeon.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SchoolService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public SchoolService(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public Student createStudent(StudentCreateRequest request){
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("선생님을 찾을 수 없습니다."));

        Student student = new Student(request.getName(), request.getGrade(), request.getClassNumber()
        , request.getStudentNumber(), request.getPhone());

        teacher.addStudent(student);

        return studentRepository.save(student);
    }

    public Teacher createTeacher(TeacherCreateRequest request){
        Teacher teacher = new Teacher(request.getName(), request.getSubject(),
                request.getPhone());

        return teacherRepository.save(teacher);
    }
}
