package algorithms.DIPLOMA.util.read_write.impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class XLSParser {
    private File inputFile;

    public XLSParser(File inputFile) {
        this.inputFile = inputFile;
    }

    public ArrayList<String> parse() {
        ArrayList<String> parsedXLSStrings = new ArrayList<>();

        try {
            FileInputStream excelFile = new FileInputStream(inputFile);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet dataSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = dataSheet.iterator();
            iterator.next();

            while (iterator.hasNext()){
                StringBuilder stringBuilder = new StringBuilder();

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()){
                    Cell currentCell = cellIterator.next();
                    stringBuilder.append(new DataFormatter().formatCellValue(currentCell)).append(";");
                }

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
