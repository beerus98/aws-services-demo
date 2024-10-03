package com.example.demo.controller;


import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/user")
    public ResponseEntity<?> getUser(){
        return new ResponseEntity<>(userService.getUser(),HttpStatus.OK);
    }

    @PostMapping(value = "/getUser")
    public ResponseEntity<?> saveUser(@RequestParam(value = "users")List<String> user){
        userService.writeToFile(user);
        return  ResponseEntity.ok("Users added in the File Successfully");
    }






}
