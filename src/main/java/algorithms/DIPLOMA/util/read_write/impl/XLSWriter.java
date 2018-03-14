package algorithms.DIPLOMA.util.read_write.impl;

import algorithms.DIPLOMA.model.Genome;
import algorithms.DIPLOMA.model.Teacher;
import algorithms.DIPLOMA.util.read_write.Writer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class XLSWriter implements Writer {

    private final String FILE_NAME = "C:\\Users\\aiksanov\\IdeaProjects\\GA\\src\\main\\resources\\xlsx\\test.xlsx";

    @Override
    public void write(Map<String, ArrayList<Genome>> data) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("RESULT");

        int rowNum = 0;
        ArrayList<String> days = sortDaysInWeekOrder(data.keySet());

        for (String s : days){
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(s);
            row = sheet.createRow(rowNum++);

            ArrayList<Genome> genomes = data.get(s);

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
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private ArrayList<String> sortDaysInWeekOrder(Set<String> keys){
        ArrayList<String> daysOfTheWeek = new ArrayList<>(keys);

        Collections.sort(daysOfTheWeek,(String o1, String o2) -> {
            Map<String, Integer> intValuesForStrings = new HashMap<>();

            intValuesForStrings.put("Monday", 1);
            intValuesForStrings.put("Tuesday", 2);
            intValuesForStrings.put("Wednesday", 3);
            intValuesForStrings.put("Thursday", 4);
            intValuesForStrings.put("Friday", 5);
            intValuesForStrings.put("Saturday", 6);
            intValuesForStrings.put("Sunday", 7);

            int i1 = intValuesForStrings.get(o1);
            int i2 = intValuesForStrings.get(o2);

            return (i1 < i2 ? -1 : (i1 == i2 ? 0 : 1));
        });

        return daysOfTheWeek;
    }
}
