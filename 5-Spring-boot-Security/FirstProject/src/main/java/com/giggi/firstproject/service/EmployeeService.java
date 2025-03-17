package com.giggi.firstproject.service;

import com.giggi.firstproject.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    public void saveEmployee(Employee employee);
    public List<Employee> getEmployees();
    public Employee getEmployee(int id);
    public void deleteEmployee(int id);
    public void updateEmployee(Employee employee);
}
