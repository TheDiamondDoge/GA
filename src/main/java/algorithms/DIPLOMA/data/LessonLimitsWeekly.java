package algorithms.DIPLOMA.data;

import java.util.Map;

public class LessonLimitsWeekly {
    private static Map<String, Map<String, Integer>> lessonLimitsWeekly;

    public static void setLessonLimitsWeekly(Map<String, Map<String, Integer>> lessonLimitsWeekly) {
        LessonLimitsWeekly.lessonLimitsWeekly = lessonLimitsWeekly;
    }

    public static Map<String, Integer> getGradeWeeklyLimit(String grade){
        return lessonLimitsWeekly.get(grade);
    }
}
