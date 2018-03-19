import algorithms.DIPLOMA.TimetableCreationAlgorithm;
import algorithms.DIPLOMA.data.GradeDataObject;
import algorithms.DIPLOMA.util.PropertiesExtractor;
import algorithms.DIPLOMA.util.TeachersCreator;
import algorithms.DIPLOMA.util.printers.DayPrinter;
import algorithms.DIPLOMA.util.read_write.impl.XLSParser;
import algorithms.DIPLOMA.util.read_write.impl.XLSWriter;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        PropertiesExtractor.setPropertiesFile(new File("app.properties"));
        File inputFile = new File(PropertiesExtractor.getInputFilepath());
        File outputFile = new File(PropertiesExtractor.getOutputFilepath());

        XLSParser xlsParser = new XLSParser(inputFile);
        ArrayList<String> parsedXLSStrings = xlsParser.parse();
        TeachersCreator teachersCreator = new TeachersCreator(parsedXLSStrings);
        teachersCreator.createTeachers();

        TimetableCreationAlgorithm timetableCreationAlgorithm = new TimetableCreationAlgorithm();
        DayPrinter dayPrinter = new DayPrinter();

        for (int x = 1; x < 6; x++) {
            dayPrinter.printDayOfTheWeek(x);
            timetableCreationAlgorithm.initTeachersPool(x);
            GradeDataObject.grade = 1;

            for (int i = 0; i < 3; i++) {
                timetableCreationAlgorithm.start();
            }
        }

        XLSWriter xlsWriter = new XLSWriter(outputFile);
        xlsWriter.write(timetableCreationAlgorithm.getResult());
    }
}
