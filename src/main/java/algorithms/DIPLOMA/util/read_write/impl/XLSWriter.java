package algorithms.DIPLOMA.util.read_write.impl;

import algorithms.DIPLOMA.model.*;
import algorithms.DIPLOMA.util.PropertiesExtractor;
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

    public void write(Map<Integer, ArrayList<Genome>> data) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("RESULT");

        int rowNum = 0;
        ArrayList<Integer> days = new ArrayList<>();
        days.addAll(data.keySet());
        Collections.sort(days);

        for (Integer i : days){
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(DayPrinter.dayOfTheWeekFromNumber(i));
            row = sheet.createRow(rowNum++);

            ArrayList<Genome> genomes = data.get(i);

            for (Genome genome : genomes){
                int colNum = 0;
                for (Teacher teacher : genome.getDay()){
                    cell = row.createCell(colNum++);
                    cell.setCellValue(teacher.toString());
                }
                row = sheet.createRow(rowNum++);
            }
        }

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
}
