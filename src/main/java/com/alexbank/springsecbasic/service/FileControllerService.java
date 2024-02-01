package com.alexbank.springsecbasic.service;

import com.alexbank.springsecbasic.entity.BadGuyFile;
import com.alexbank.springsecbasic.helpers.ExcelSheetRow;
import com.alexbank.springsecbasic.helpers.ReadExcelData;
import com.alexbank.springsecbasic.helpers.TextFileHandler;
import com.alexbank.springsecbasic.helpers.UploadExcelFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileControllerService {
    private final ReadExcelData readExcelData;
    private final TextFileHandler textFileHandler;
    private final UploadExcelFile uploadExcelFile;
    private final BadGuyFileService badGuyFileService;

    @Autowired
    public FileControllerService(ReadExcelData readExcelData, TextFileHandler textFileHandler, UploadExcelFile uploadExcelFile, BadGuyFileService badGuyFileService) {
        this.readExcelData = readExcelData;
        this.textFileHandler = textFileHandler;
        this.uploadExcelFile = uploadExcelFile;
        this.badGuyFileService = badGuyFileService;
    }

    public String addBadGuy(MultipartFile multipartFile) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BadGuyFile badGuyFile = new BadGuyFile();
        if (authentication != null) {
            badGuyFile.setSessionUsername(authentication.getName());
        }
        //Step 1: upload Excel file to its directory
        badGuyFile = uploadExcelFile.uploadExcelFile(multipartFile, badGuyFile);
        if (badGuyFile.getStatus().equals("SUCCESS")) {

            //Step 2: get the latest text file name from DB
            String textFileName =textFileHandler.getLatestTextFileName();

            //Step 3: move data from old text file to the new one with its data
            String newTextFileName = textFileHandler.createNewTextFile(textFileName);
            badGuyFile.setGeneratedTextName(newTextFileName);

            //Step 4: read Excel data
            List<ExcelSheetRow> excelSheetRows = readExcelData.readExcelData(badGuyFile.getUploadedExcelName());

            //Step 5: append Data to the new text file
            textFileHandler.appendDataToNewTextFile(excelSheetRows,newTextFileName);

        }

        badGuyFileService.insert(badGuyFile);
        return badGuyFile.getStatus();
    }
}
