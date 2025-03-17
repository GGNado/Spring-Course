package com.giggi.firstproject.dao;

import com.giggi.firstproject.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    public void saveEmployee(Employee employee);
    public List<Employee> getEmployees();
    public Employee getEmployee(int id);
    public void deleteEmployee(int id);
    public void updateEmployee(Employee employee);
}
