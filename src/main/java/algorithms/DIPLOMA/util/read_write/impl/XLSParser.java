package algorithms.DIPLOMA.util.read_write.impl;

import algorithms.DIPLOMA.model.Teacher;
import algorithms.DIPLOMA.util.PropertiesExtractor;
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


public class XLSParser {

    private String FILE_NAME;
//    private static List<Teacher> teachers;
//    private static ArrayList<String> parsedXLSFile = new ArrayList<>();

    public XLSParser() {
        FILE_NAME = PropertiesExtractor.getInputFilepath();
    }

    public ArrayList<String> parse() {
        ArrayList<String> parsedXLSStrings = new ArrayList<>();

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
                        stringBuilder.append(new DataFormatter().formatCellValue(currentCell)).append(";");
                    } else if (currentCell.getColumnIndex() == 2){
                        stringBuilder.append(new DataFormatter().formatCellValue(currentCell)).append(";");
                    }
                }

                //parsedXLSFile.add(stringBuilder.toString());
                parsedXLSStrings.add(stringBuilder.toString());
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return parsedXLSStrings;
    }


}
