package com.FileManagementSystem.Controller;

import com.FileManagementSystem.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        String uploadImage=fileService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName){
        byte[] fileData = fileService.downloadFile(fileName);
        return ResponseEntity.status(HttpStatus.OK).
                contentType(MediaType.valueOf("image/png")).
                body(fileData);
    }

}
