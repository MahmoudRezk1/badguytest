package com.alexbank.springsecbasic.helpers;

import com.alexbank.springsecbasic.service.BadGuyFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@Scope("prototype")
public class TextFileHandler {
    private final BadGuyFileService badGuyFileService;

    @Autowired
    public TextFileHandler(BadGuyFileService badGuyFileService) {
        this.badGuyFileService = badGuyFileService;
    }

    public String getLatestTextFileName() {

        String textFileName = badGuyFileService.getLatestTextFileName();
        if (textFileName == null || textFileName.isEmpty() || textFileName.isBlank()) {
            return "INDBGY.txt";
        }
        return textFileName;
    }

    public String createNewTextFile(String textFileName) throws IOException {
        String directory = "E:\\Projects\\Java project\\alex bank\\testcopy\\";
        File file = new File(directory + textFileName);
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fis.read(bytes);

        String[] parts = textFileName.split("\\.");
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss a");
        String newFileName = parts[0] + "." + format.format(new Date()) + "." + "txt";
        File newFile = new File(directory + newFileName);

        FileOutputStream fos = new FileOutputStream(newFile);
        fos.write(bytes);
        fos.flush();
        fos.close();
        fis.close();
        return newFileName;
    }

    public void appendDataToNewTextFile(List<ExcelSheetRow> excelSheetRows, String newTextFile) throws IOException {
        String directory = "E:\\Projects\\Java project\\alex bank\\testcopy\\";
        File file = new File(directory + newTextFile);
        BufferedReader br = new BufferedReader(new FileReader(file));


        PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
        for (int i = 0; i< excelSheetRows.size(); i++) {
            ExcelSheetRow row = excelSheetRows.get(i);
            String line = br.readLine();
            if (line == null) line="";
            if (line.startsWith("name")) {
                continue;
            }
            writer.print(row.getName() + ";");
            writer.print(row.getAge() + ";");
            writer.print(row.getEmail() + ";");
            writer.println();
        }
        writer.flush();
        writer.close();
    }
}
