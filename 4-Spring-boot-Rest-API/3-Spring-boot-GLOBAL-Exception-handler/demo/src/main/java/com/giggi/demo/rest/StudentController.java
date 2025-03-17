package com.giggi.demo.rest;

import com.giggi.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        // Check if the studentId is valid
        if (studentId < 0 || studentId >= students.size()) {
            // Lancia l'eccezione
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }


        return students.get(studentId);

    }

}






