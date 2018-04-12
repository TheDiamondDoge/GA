import algorithms.DIPLOMA.TimetableCreationAlgorithm;
import algorithms.DIPLOMA.data.GradeDataObject;
import algorithms.DIPLOMA.data.LessonLimitsDaily;
import algorithms.DIPLOMA.data.LessonLimitsWeekly;
import algorithms.DIPLOMA.model.Genome;
import algorithms.DIPLOMA.util.PropertiesExtractor;
import algorithms.DIPLOMA.util.creators.LimitsCreatorDaily;
import algorithms.DIPLOMA.util.creators.LimitsCreatorWeekly;
import algorithms.DIPLOMA.util.creators.TeachersCreator;
import algorithms.DIPLOMA.util.printers.DayPrinter;
import algorithms.DIPLOMA.util.read_write.impl.XLSParser;
import algorithms.DIPLOMA.util.read_write.impl.XLSWriter;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        PropertiesExtractor.setPropertiesFile(new File("app.properties"));
        File inputFile = new File(PropertiesExtractor.getInputFilepath());
        File outputFile = new File(PropertiesExtractor.getOutputFilepath());

        XLSParser xlsParserLimits = new XLSParser(new File(PropertiesExtractor.getDaylimitsFilepath()));
        LimitsCreatorDaily limitsCreatorDaily = new LimitsCreatorDaily(xlsParserLimits.parse());
        LessonLimitsDaily.setLessonLimits(limitsCreatorDaily.createLimitsFromList());

        xlsParserLimits = new XLSParser(new File(PropertiesExtractor.getWeeklimitsFilepath()));
        LimitsCreatorWeekly limitsCreatorWeekly = new LimitsCreatorWeekly(xlsParserLimits.parse());
        LessonLimitsWeekly.setLessonLimitsWeekly(limitsCreatorWeekly.createLimitsFromList());

        XLSParser xlsParser = new XLSParser(inputFile);
        ArrayList<String> parsedXLSStrings = xlsParser.parse();
        TeachersCreator teachersCreator = new TeachersCreator(parsedXLSStrings);
        teachersCreator.createTeachers();
        List<String> grades = teachersCreator.getAllGradesFromXls();

        TimetableCreationAlgorithm timetableCreationAlgorithm = new TimetableCreationAlgorithm();
        Map<Integer, ArrayList<Genome>> timetable = new HashMap<>();
        DayPrinter dayPrinter = new DayPrinter();

        grades.remove("1f");
        for (int x = 1; x < 6; x++) {
            TimetableCreationAlgorithm.setGradesCreated(0);
            ArrayList<Genome> temp = new ArrayList<>();
            while (TimetableCreationAlgorithm.getGradesCreated() != grades.size()) {
                temp = new ArrayList<>();
                TimetableCreationAlgorithm.setGradesCreated(0);
                dayPrinter.printDayOfTheWeek(x);
                timetableCreationAlgorithm.initTeachersPool(x);

                for (String grade : grades) {
                    GradeDataObject.GRADE = grade;

                    Genome genome = timetableCreationAlgorithm.start();
                    if (genome == null)
                        break;

                    temp.add(genome);
                }
            }
            //TODO THINK TWICE!!!!
            temp.forEach(Genome::weeklyLimitsAdjustment);
            timetable.put(x, temp);
        }

        XLSWriter xlsWriter = new XLSWriter(outputFile);
        xlsWriter.write(timetable);
    }
}
