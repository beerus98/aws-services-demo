package com.example.demo.serviceimpl;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Value("${file.output.path}")
    private String basePath;


    @Override
    public List<String> getUser() {
        return List.of("vishal","sneha","Ramsy Bolton");
    }

    @Override
    public void writeToFile(List<String> users) {

        String fileName = "output_" + System.currentTimeMillis() + ".txt";
        Path filePath = Path.of(basePath, fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            writer.write(String.valueOf(users));
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
