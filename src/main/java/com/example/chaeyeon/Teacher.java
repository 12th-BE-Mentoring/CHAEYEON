package com.example.chaeyeon;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 이름
    private String subject; // 담당과목
    private String phone; // 전화번호

    @OneToMany(mappedBy = "homeroomTeacher")
    private List<Student> students = new ArrayList<>();

    public Teacher() {
    }

    public List<Student> getStudents() {
        return students;
    }

    public String getPhone() {
        return phone;
    }

    public String getSubject() {
        return subject;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

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
