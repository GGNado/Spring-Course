package com.giggi.firstproject.service;

import com.giggi.firstproject.dao.EmployeeRepository;
import com.giggi.firstproject.entity.Employee;
import com.giggi.firstproject.exception.employee.EmployeeNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(int id) {
        Optional<Employee> result = employeeRepository.findById(id);
        Employee employee = null;

        if (result.isPresent()) {
            employee = result.get();
        } else {
            throw new EmployeeNotFoundException("Employee not found for id: " + id);
        }

        return employee;
    }

    @Transactional
    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
