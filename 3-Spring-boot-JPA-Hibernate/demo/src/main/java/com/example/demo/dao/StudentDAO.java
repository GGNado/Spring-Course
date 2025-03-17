package com.example.demo.dao;

import com.example.demo.entity.Student;

import java.util.List;

public interface StudentDAO {
    public void saveStudent(Student student);
    public Student findStudentById(int id);
    List<Student> findAllStudents();
    Student findStudentByLastName(String lastName);
    void updateStudent(Student student);
    int deleteStudent(int id);
    int deleteAllStudents();
}
