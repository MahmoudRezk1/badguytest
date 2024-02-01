package com.alexbank.springsecbasic.controller;

import com.alexbank.springsecbasic.service.FileControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {
    private final FileControllerService fileControllerService;
    @Autowired
    public FileController(FileControllerService fileControllerService) {
        this.fileControllerService = fileControllerService;
    }

    @PostMapping("upload")
    public String addingFile(@RequestParam(name = "file") MultipartFile file) throws IOException {
       return fileControllerService.addBadGuy(file);
    }
}
