package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {



    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(value = "/employee/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable("id") String id){
        try {
              if (ObjectUtils.isEmpty(id)){
                  throw  new BadRequestException("Invalid id");
              }
            return new ResponseEntity<>(employeeRepository.getEmployeeById(id), HttpStatus.OK);
        }catch (BadRequestException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>("Error while fetching the Employee",HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


//    @GetMapping(value = "/employee/all")
//    public ResponseEntity<?> getAllEmployee(){
//        try {
//
//            return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
//        }
//        catch (Exception e){
//            return new ResponseEntity<>("Error while fetching the Employees",HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//
//    }

    @PostMapping(value = "/employee")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee){
        try {
            if (ObjectUtils.isEmpty(employee)){
                throw  new BadRequestException("Invalid details");
            }


            return new ResponseEntity<>(employeeRepository.save(employee),HttpStatus.CREATED);
        }catch (BadRequestException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>("Error while saving the Employee",HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @DeleteMapping(value = "/employee")
    public ResponseEntity<?> deleteEmployee(@RequestBody Employee employee){
        try {
            if (ObjectUtils.isEmpty(employee)){
                throw  new BadRequestException("Invalid details");
            }
          //  employeeService.deleteEmployee(employee.getId());

            return new ResponseEntity<>(employeeRepository.deleteEmployeeById(employee.getId()),HttpStatus.OK);
        }catch (BadRequestException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>("Error while deleting the Employee",HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
