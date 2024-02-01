package com.alexbank.springsecbasic.helpers;

import com.alexbank.springsecbasic.entity.BadGuyFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Scope("prototype")
public class UploadExcelFile {

    public BadGuyFile uploadExcelFile(MultipartFile file,BadGuyFile badGuyFile){
        try {
            String directory = "E:\\Projects\\Java project\\alex bank\\testcopy\\";
            String fileFinalName = checkFileExistence(directory, file.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(directory + fileFinalName);

            byte[] b = file.getBytes();

            fos.write(b);
            fos.flush();
            fos.close();
            badGuyFile.setDate(new Date());
            badGuyFile.setUploadedExcelName(fileFinalName);
            badGuyFile.setStatus("SUCCESS");
            return badGuyFile;
        }catch (IOException e){
            badGuyFile.setStatus("FAILED");
            return badGuyFile;
        }
    }
    public String checkFileExistence(String Directory, String fileOrignalName) {
        File temp = new File(Directory + fileOrignalName);
        if (temp.exists()) {
            String[] fileParts = fileOrignalName.split("\\.");
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss a");
            String fileNameWithoutExtension = fileParts[fileParts.length - 2] + "." + dateFormat.format(new Date());
            fileParts[fileParts.length - 2] = fileNameWithoutExtension;

            StringBuilder fileNameWithExtension = new StringBuilder();
            for (int i = 0; i < fileParts.length; i++) {
                fileNameWithExtension.append(fileParts[i]);
                if (i == fileParts.length - 2) {
                    fileNameWithExtension.append(".");
                }
            }

            return fileNameWithExtension.toString();
        } else {
            return fileOrignalName;
        }

    }

}
