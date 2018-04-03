import algorithms.DIPLOMA.TimetableCreationAlgorithm;
import algorithms.DIPLOMA.data.GradeDataObject;
import algorithms.DIPLOMA.util.PropertiesExtractor;
import algorithms.DIPLOMA.util.TeachersCreator;
import algorithms.DIPLOMA.util.printers.DayPrinter;
import algorithms.DIPLOMA.util.read_write.impl.XLSParser;
import algorithms.DIPLOMA.util.read_write.impl.XLSWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PropertiesExtractor.setPropertiesFile(new File("app.properties"));
        File inputFile = new File(PropertiesExtractor.getInputFilepath());
        File outputFile = new File(PropertiesExtractor.getOutputFilepath());

        XLSParser xlsParser = new XLSParser(inputFile);
        ArrayList<String> parsedXLSStrings = xlsParser.parse();
        TeachersCreator teachersCreator = new TeachersCreator(parsedXLSStrings);
        teachersCreator.createTeachers();
        List<String> grades = teachersCreator.getAllGradesFromXls();

        TimetableCreationAlgorithm timetableCreationAlgorithm = new TimetableCreationAlgorithm();
        DayPrinter dayPrinter = new DayPrinter();

        for (int x = 1; x < 2; x++) {
            dayPrinter.printDayOfTheWeek(x);
            timetableCreationAlgorithm.initTeachersPool(x);

            for (String grade : grades) {
                GradeDataObject.GRADE = grade;
                timetableCreationAlgorithm.start();
            }
        }

        XLSWriter xlsWriter = new XLSWriter(outputFile);
        xlsWriter.write(timetableCreationAlgorithm.getResult());
    }
}
