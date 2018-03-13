package algorithms.DIPLOMA.util.read_write.impl;

import algorithms.DIPLOMA.model.Teacher;
import algorithms.DIPLOMA.util.read_write.Parser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public class XLSParser implements Parser {

    private final String FILE_NAME = "C:\\Users\\aiksanov\\IdeaProjects\\GA\\src\\main\\resources\\init.xlsx";
    private static List<Teacher> teachers = new ArrayList<>();


    public List<Teacher> getTeachersForDay(int dayOfTheWeek){
        return teachers.stream().filter(teacher -> teacher.getDayOfTheWeek() == dayOfTheWeek).collect(Collectors.toList());
    }

    @Override
    public void parse() {
        if (teachers.size() != 0)
            return;

        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet dataSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = dataSheet.iterator();

            while (iterator.hasNext()){
                String teachersName = "";
                int lesson = 0;
                int day = 0;

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()){
                    Cell currentCell = cellIterator.next();

                    if(currentCell.getColumnIndex() == 0){
                        teachersName = currentCell.getStringCellValue();
                    } else if (currentCell.getColumnIndex() == 1){
                        lesson = (int) currentCell.getNumericCellValue();
                    } else if (currentCell.getColumnIndex() == 2){
                        day = (int) currentCell.getNumericCellValue();
                    }
                }
                teachers.add(new Teacher(teachersName, lesson, day));
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
