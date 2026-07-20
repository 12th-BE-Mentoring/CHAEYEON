package com.example.chaeyeon.domain.teacher;

import com.example.chaeyeon.domain.student.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Teacher {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 이름
    private String subject; // 담당과목
    private String phone; // 전화번호

    @OneToMany(mappedBy = "homeroomTeacher")
    private List<Student> students = new ArrayList<>();

    public Teacher(String name, String subject, String phone) {
        this.name = name;
        this.subject = subject;
        this.phone = phone;
    }

    public void addStudent(Student student){
        this.students.add(student);
        if(student.getHomeroomTeacher() != this) {
            student.setHomeroomTeacher(this);
        }
    }

    public void rmStudent(Student student) {
        this.students.remove(student);
        if(student.getHomeroomTeacher() == this) {
            student.setHomeroomTeacher(null);
        }
    }
}
