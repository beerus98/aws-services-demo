package com.example.demo.service;

import java.util.List;

public interface UserService {
    List<String> getUser();
    void writeToFile(List<String> users);
}
