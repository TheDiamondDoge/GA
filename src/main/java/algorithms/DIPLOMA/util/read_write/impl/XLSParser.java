package algorithms.DIPLOMA.util.read_write.impl;

import algorithms.DIPLOMA.model.Teacher;
import algorithms.DIPLOMA.util.read_write.Parser;
import org.apache.poi.ss.usermodel.*;
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

    private final String FILE_NAME = "C:\\Users\\aiksanov\\IdeaProjects\\GA\\src\\main\\resources\\xlsx\\init.xlsx";
    private static List<Teacher> teachers = new ArrayList<>();
    private static ArrayList<String> parsedXLSFile = new ArrayList<>();


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
                StringBuilder stringBuilder = new StringBuilder();

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()){
                    Cell currentCell = cellIterator.next();

                    if((currentCell.getColumnIndex() == 0) || (currentCell.getColumnIndex() == 1)){
                        stringBuilder.append(new DataFormatter().formatCellValue(currentCell) + ";");
                    } else if (currentCell.getColumnIndex() == 2){
                        stringBuilder.append(new DataFormatter().formatCellValue(currentCell) + ";");
                    }
                }

                parsedXLSFile.add(stringBuilder.toString());
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void createLessons(){
        ArrayList<Integer> lessons;
        for (String str : parsedXLSFile){
            String[] objectFields = str.split(";");
            lessons = getAllLessons(objectFields[1]);

            for (int i : lessons){
                teachers.add(new Teacher(objectFields[0], i, Character.getNumericValue(objectFields[2].charAt(0))));
            }
        }
    }

    protected ArrayList<Integer> getAllLessons(String rangeOfLessons){
        String[] lessons = rangeOfLessons.split(",");
        ArrayList<Integer> result = new ArrayList<>();

        for (String str : lessons){
            if (str.contains("-")){
                for (int i = Character.getNumericValue(str.charAt(0));
                     i <= Character.getNumericValue(str.charAt(2)); i++)
                {
                    result.add(i);
                }
            } else {
                result.add(Character.getNumericValue(str.charAt(0)));
            }
        }
        return result;
    }
}
