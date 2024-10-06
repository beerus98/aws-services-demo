package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    String uploadFile(MultipartFile multipartFile);

    byte [] downloadFile(String filename);

    String deleteFile(String filename);

}
