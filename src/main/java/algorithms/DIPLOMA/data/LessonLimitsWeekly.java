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

    public static void adjustmentFroGrade(Map<String, Integer> adjustedWeeklyLimit, String grade){
        Map<String, Integer> temp = lessonLimitsWeekly.get(grade);
        for (String subject : adjustedWeeklyLimit.keySet()){
            int lessonAmount = adjustedWeeklyLimit.get(subject);

            temp.put(subject, temp.get(subject) - lessonAmount);
        }
        lessonLimitsWeekly.put(grade, temp);
    }
}
