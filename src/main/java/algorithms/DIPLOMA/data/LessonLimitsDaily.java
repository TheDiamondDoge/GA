package algorithms.DIPLOMA.data;

import java.util.Map;

public class LessonLimitsDaily {
    private static Map<String, Integer> lessonLimits;

    public static void setLessonLimits(Map<String, Integer> limits) {
        lessonLimits = limits;
    }

    public static int getLessonLimit(String grade){
        return lessonLimits.get(grade);
    }
}
