import algorithms.DIPLOMA.DailyTimetableCreator;
import algorithms.DIPLOMA.data.GradeDataObject;
import algorithms.DIPLOMA.data.LessonLimitsDaily;
import algorithms.DIPLOMA.data.LessonLimitsWeekly;
import algorithms.DIPLOMA.model.Day;
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

        XLSParser xlsParserLimits = new XLSParser(
                new File(PropertiesExtractor.getDaylimitsFilepath()));
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
        grades.remove("1f");

        DailyTimetableCreator dailyTimetableCreator = new DailyTimetableCreator();
        Map<Integer, ArrayList<Day>> gradesTimetable = new HashMap<>();
        DayPrinter dayPrinter = new DayPrinter();

        Integer[] d = {1,2,3,4,5};
        List<Integer> days = Arrays.asList(d);

        for (int x : days) {
            DailyTimetableCreator.setGradesCreated(0);
            ArrayList<Day> temp = new ArrayList<>();
            while (DailyTimetableCreator.getGradesCreated() != grades.size()) {
                temp = new ArrayList<>();
                DailyTimetableCreator.setGradesCreated(0);
                dayPrinter.printDayOfTheWeek(x);
                dailyTimetableCreator.initTeachersPool(x);

                for (String grade : grades) {
                    GradeDataObject.GRADE = grade;
                    Day day = dailyTimetableCreator.start(x);
                    temp.add(day);
                }
            }

            temp.forEach(Day::weeklyLimitsAdjustment);
            gradesTimetable.put(x, temp);
        }

        XLSWriter xlsWriter = new XLSWriter(outputFile);
        xlsWriter.write(gradesTimetable);
    }
}
