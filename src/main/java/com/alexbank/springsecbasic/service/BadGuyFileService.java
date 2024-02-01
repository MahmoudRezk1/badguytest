package com.alexbank.springsecbasic.service;

import com.alexbank.springsecbasic.entity.BadGuyFile;
import com.alexbank.springsecbasic.repository.BadGuyFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadGuyFileService {
    private final BadGuyFileRepository badGuyFileRepository;
    @Autowired
    public BadGuyFileService(BadGuyFileRepository badGuyFileRepository) {
        this.badGuyFileRepository = badGuyFileRepository;
    }

    public BadGuyFile insert(BadGuyFile badGuyFile){
        return badGuyFileRepository.save(badGuyFile);
    }
    public String getLatestTextFileName(){
     return badGuyFileRepository.getLatestTextFileName();
    }
}
