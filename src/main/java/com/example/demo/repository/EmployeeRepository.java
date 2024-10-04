package com.example.demo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Employee save(Employee employee){
        dynamoDBMapper.save(employee);
        return employee;
    }

    public Employee getEmployeeById(String id){
        return dynamoDBMapper.load(Employee.class,id);
    }

    public String deleteEmployeeById(String id){
        Employee emp = dynamoDBMapper.load(Employee.class,id);
        dynamoDBMapper.delete(emp);
        return "Employee Deleted";
    }

}
