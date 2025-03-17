package com.giggi.firstproject.rest;

import com.giggi.firstproject.entity.Employee;
import com.giggi.firstproject.exception.employee.EmployeeNotFoundException;
import com.giggi.firstproject.exception.general.IdNotValidException;
import com.giggi.firstproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {

        if (employeeId < 0) {
            throw new IdNotValidException("Stai usando un id non valido - " + employeeId);
        }
        Employee employee = employeeService.getEmployee(employeeId);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee) {
        System.out.println(employee.getEmail());
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        if (employeeId < 0) {
            throw new IdNotValidException("Stai usando un id non valido - " + employeeId);
        }
        Employee employee = employeeService.getEmployee(employeeId);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
        }
        employeeService.deleteEmployee(employeeId);
        return "Deleted employee id - " + employeeId;
    }

}
