package com.alexbank.springsecbasic.helpers;

import org.apache.poi.ss.usermodel.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Component
@Scope("prototype")
public class ReadExcelData {
    public List<ExcelSheetRow> readExcelData(String excelFileName) throws IOException {
        String directory = "E:\\Projects\\Java project\\alex bank\\testcopy\\";
        File file = new File(directory+excelFileName);
        Workbook workbook = WorkbookFactory.create(new FileInputStream(file));

        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        List<ExcelSheetRow> excelSheetRows = new ArrayList<>();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.iterator();
            ExcelSheetRow record = new ExcelSheetRow();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int index = cell.getColumnIndex();
                switch (index) {
                    case 0:
                        record.setName(cell.toString());
                    case 1:
                        record.setAge(cell.toString());
                    case 2:
                        record.setEmail(cell.toString());

                }
            }
            excelSheetRows.add(record);
        }
        workbook.close();
        return excelSheetRows;
    }
}
