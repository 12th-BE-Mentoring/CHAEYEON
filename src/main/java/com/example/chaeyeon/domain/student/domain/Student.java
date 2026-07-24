package com.example.chaeyeon.domain.student.domain;

import com.example.chaeyeon.domain.teacher.domain.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //이름

    private int grade; // 학년

    private int classNumber; // 반
    private int studentNumber; // 번호

    private String phone; // 전화번호

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher homeroomTeacher; // 담임 선생남

    public Student(String name, int grade, int classNumber, int studentNumber, String phone) {
        this.name = name;
        this.grade = grade;
        this.classNumber = classNumber;
        this.studentNumber = studentNumber;
        this.phone = phone;
    }

    public void setHomeroomTeacher(Teacher teacher){
        this.homeroomTeacher = teacher;
    }

    public void changeClassInfo(int grade ,int classNumber, int studentNumber){
        this.grade = grade;
        this.classNumber = classNumber;
        this.studentNumber = studentNumber;
    }

}
