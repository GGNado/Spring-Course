package com.example.demo;

import com.example.demo.dao.StudentDAO;
import com.example.demo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.Format;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
            createStudent(studentDAO);
            //findStudentById(studentDAO);
            //getAllStudents(studentDAO);
            //updateStudent(studentDAO);
            //findStudentById(studentDAO, 1);
            //deleteStudent(studentDAO, 1);
            //deleteAllStudents(studentDAO);
        };
    }

    private void deleteAllStudents(StudentDAO studentDAO) {
        System.out.println("Deleting all students...");
        int rows = studentDAO.deleteAllStudents();
        System.out.println("Rows deleted: " + rows);
    }

    private void deleteStudent(StudentDAO studentDAO, int i) {
        System.out.println("Deleting student...");
        int rows = studentDAO.deleteStudent(i);
        System.out.println("Rows deleted: " + rows);
    }

    private void updateStudent(StudentDAO studentDAO) {
        Student student = studentDAO.findStudentById(1);
        student.setFirstName("Luigi");
        studentDAO.updateStudent(student);
    }

    private void getAllStudents(StudentDAO studentDAO) {
        System.out.println("Getting all students...");
        for (Student student : studentDAO.findAllStudents()){
            System.out.println(student);
        }

    }

    private void findStudentById(StudentDAO studentDAO, int id) {
        System.out.println("Finding student by id...");
        Student student = studentDAO.findStudentById(id);
        System.out.println("Student found: " + student);

    }

    private void createStudent(StudentDAO studentDAO) {
        System.out.println("Creating student...");
        Student student = new Student("John", "Doe", "ciao@gmail.com");

        studentDAO.saveStudent(student);
        System.out.println("Student created with id: " + student.getId());
    }


}
