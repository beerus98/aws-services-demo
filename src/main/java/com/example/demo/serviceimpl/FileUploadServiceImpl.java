package com.example.demo.serviceimpl;

import com.amazonaws.services.mturk.model.ServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.demo.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${application.bucket.name}")
    private String s3Bucket;

    @Autowired
    private AmazonS3 amazonS3Client;

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        try {
            String fileName = System.currentTimeMillis() + "_" + multipartFile.getName();
            amazonS3Client.putObject(new PutObjectRequest(s3Bucket , fileName, convertFile(multipartFile)));


        } catch (Exception e) {
            throw new ServiceException("Error while uploading the file in S3 : "+e.getMessage());
        }



        return "File uploaded Successfully";
    }

    @Override
    public byte[] downloadFile(String filename) {

        try {

            S3Object object =amazonS3Client.getObject(s3Bucket,filename);

            S3ObjectInputStream inputStream = object.getObjectContent();

            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String deleteFile(String filename) {
        try {
            amazonS3Client.deleteObject(s3Bucket, filename);
            return "file Deleted Successfully";
        } catch (Exception e){
            throw new RuntimeException("Error while deleting the object from S3 : " + e.getCause());
        }
    }


    private File convertFile (MultipartFile multipartFile){
        File convertFile = new File(multipartFile.getName());
        try (FileOutputStream fos = new FileOutputStream(convertFile)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return convertFile;

    }
}
