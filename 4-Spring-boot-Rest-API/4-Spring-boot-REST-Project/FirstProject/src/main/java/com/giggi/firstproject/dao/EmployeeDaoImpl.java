package com.giggi.firstproject.dao;

import com.giggi.firstproject.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    @Override
    public List<Employee> getEmployees() {

        return entityManager.createQuery("from Employee").getResultList();
    }

    @Override
    public Employee getEmployee(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public void deleteEmployee(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        entityManager.merge(employee);
    }
}
