package com.giggi.demo.rest;

import com.giggi.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private List<Student> students;

    @PostConstruct
    public void init() {
        System.out.println("StudentController bean has been created!");
        students = new ArrayList<>();
        students.add(new Student("John", "Doe"));
        students.add(new Student("Jane", "Adbv"));
        students.add(new Student("Jack", "Whiute"));
    }

    @GetMapping("/all")
    public List<Student> all() {
        return students;
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId) {

        return students.get(studentId);

    }
}
