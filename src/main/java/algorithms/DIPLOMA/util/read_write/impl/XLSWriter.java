package algorithms.DIPLOMA.util.read_write.impl;

import algorithms.DIPLOMA.model.*;
import algorithms.DIPLOMA.util.printers.DayPrinter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class XLSWriter {

    private File outputXLS;

    public XLSWriter(File outputXLS) {
        this.outputXLS = outputXLS;
    }

    public void write(Map<Integer, ArrayList<Day>> data) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet gradesSheet = workbook.createSheet("Классы");
        XSSFSheet teachersSheet = workbook.createSheet("Учителя");
        sheetForGrades(gradesSheet, data);
        sheetForTeachers(teachersSheet, data);
        try{
            FileOutputStream outputStream = new FileOutputStream(outputXLS);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void sheetForGrades(XSSFSheet sheet, Map<Integer, ArrayList<Day>> data){
        int rowNum = 0;
        ArrayList<Integer> days = new ArrayList<>(data.keySet());
        Collections.sort(days);

        for (Integer i : days){
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(DayPrinter.dayOfTheWeekFromNumber(i));
            row = sheet.createRow(rowNum++);

            ArrayList<Day> genomes = data.get(i);

            for (Day day : genomes){
                int colNum = 0;
                cell = row.createCell(colNum++);
                cell.setCellValue(day.getDay().get(0).getGrade());
                for (Teacher teacher : day.getDay()){
                    cell = row.createCell(colNum++);
                    cell.setCellValue(teacher.toString());
                }
                row = sheet.createRow(rowNum++);
            }
        }
    }


    private XSSFSheet sheetForTeachers(XSSFSheet sheet, Map<Integer, ArrayList<Day>> data){
        return null;
    }

}
