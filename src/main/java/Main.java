import algorithms.DIPLOMA.TimetableCreationAlgorithm;
import algorithms.DIPLOMA.util.PropertiesExtractor;
import algorithms.DIPLOMA.util.printers.DayPrinter;
import algorithms.DIPLOMA.util.read_write.impl.XLSParser;
import algorithms.DIPLOMA.util.read_write.impl.XLSWriter;

public class Main {

    public static void main(String[] args) {
        PropertiesExtractor.setPropertiesFile("app.config");
        XLSParser xlsParser = new XLSParser();
        xlsParser.parse();
        xlsParser.createLessons();
        TimetableCreationAlgorithm timetableCreationAlgorithm = new TimetableCreationAlgorithm();
        DayPrinter dayPrinter = new DayPrinter();

        for (int x = 1; x < 6; x++) {
            dayPrinter.printDayOfTheWeek(x);
            timetableCreationAlgorithm.initTeachersPool(x);

            for (int i = 0; i < 3; i++) {
                timetableCreationAlgorithm.start();
            }
        }

        XLSWriter xlsWriter = new XLSWriter();
        xlsWriter.write(timetableCreationAlgorithm.getResult());
    }
}
