package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;

import java.util.List;


public interface EmployeeService {

    Employee getEmployee(Long id);

    void saveEmployee(EmployeeDTO employee);

    void deleteEmployee(Long id);

    List<Employee> getAllEmployee();

}
