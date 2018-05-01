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
import algorithms.DIPLOMA.util.read_write.XLSParser;
import algorithms.DIPLOMA.util.read_write.XLSWriter;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        new Algorithm().start();
    }
}
