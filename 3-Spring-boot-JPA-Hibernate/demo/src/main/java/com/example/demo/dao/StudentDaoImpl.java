package com.example.demo.dao;

import com.example.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDAO {

    private EntityManager entityManager;

    @Autowired
    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveStudent(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findStudentById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    @Transactional
    public List<Student> findAllStudents() {
        return entityManager.createQuery("from Student order by lastName", Student.class).getResultList();
    }

    @Override
    public Student findStudentByLastName(String lastName) {
        return entityManager.createQuery("from Student where lastName = :lastName", Student.class)
                .setParameter("lastName", lastName)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public int deleteStudent(int id) {
        return entityManager.createQuery("delete from Student where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    @Transactional
    public int deleteAllStudents() {
        return entityManager.createQuery("delete from Student").executeUpdate();
    }

}
