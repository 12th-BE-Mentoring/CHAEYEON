package com.example.chaeyeon.domain;

import jakarta.persistence.*;


@Entity
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //이름

    public Student(String name, int grade, int classNumber, int studentNumber, String phone) {
        this.name = name;
        this.grade = grade;
        this.classNumber = classNumber;
        this.studentNumber = studentNumber;
        this.phone = phone;
    }

    public Student() {

    }

    public String getPhone() {
        return phone;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public int getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    private int grade; // 학년

    private int classNumber; // 반
    private int studentNumber; // 번호

    private String phone; // 전화번호


    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher homeroomTeacher; // 담임 선생남

    public Teacher getHomeroomTeacher() {
        return homeroomTeacher;
    }


    public Student(String name, int grade, int classNumber, int studentNumber, String phone, Teacher teacher) {
        this.name = name;
        this.grade = grade;
        this.classNumber = classNumber;
        this.studentNumber = studentNumber;
        this.phone = phone;
        this.homeroomTeacher = teacher;
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
