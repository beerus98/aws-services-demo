package com.example.demo.controller;

import com.amazonaws.services.cognitoidp.model.HttpHeader;
import com.example.demo.service.FileUploadService;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@RestController
public class S3UploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping(value = "/upload/file")
    public ResponseEntity<?> uploadFile(@RequestParam(value = "file") MultipartFile file){
        try {
            String rs = fileUploadService.uploadFile(file);
            return new ResponseEntity<>(rs , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @GetMapping(value = "/download/file/{fileName}")
    public ResponseEntity<ByteArrayResource> getFileFromS3(@PathVariable(value = "fileName") String fileName ){

        try {
            byte[] rs = fileUploadService.downloadFile(fileName);
            ByteArrayResource resource = new ByteArrayResource(rs);

            return  ResponseEntity
                    .ok()
                    .header("Content-type" , "application/octet-stream")
                    .header("Content-disposition", "attachment; filename=\""+fileName+"\"")
                    .body(resource);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    @PostMapping(value = "/delete/file")
    public ResponseEntity<?> deleteFile(@RequestParam(value = "filename") String file){
        try {
            String rs = fileUploadService.deleteFile(file);
            return new ResponseEntity<>(rs , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}
