package com.giggi.firstproject.service;

import com.giggi.firstproject.dao.EmployeeDao;
import com.giggi.firstproject.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Transactional
    @Override
    public void saveEmployee(Employee employee) {
        employeeDao.saveEmployee(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeDao.getEmployees();
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeDao.getEmployee(id);
    }

    @Transactional
    @Override
    public void deleteEmployee(int id) {
        employeeDao.deleteEmployee(id);
    }

    @Transactional
    @Override
    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }
}
