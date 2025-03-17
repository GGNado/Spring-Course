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


    // Gestisco l'eccezione StudentNotFoundException
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, org.springframework.http.HttpStatus.NOT_FOUND);
    }

    // Aggiungo un metodo per gestire un'eccezione generica
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage()); // cambia il messaggio di errore se ti serve
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, org.springframework.http.HttpStatus.BAD_REQUEST);
    }


}






