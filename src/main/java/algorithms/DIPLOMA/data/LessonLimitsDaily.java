package algorithms.DIPLOMA.data;

import java.util.Map;

public class LessonLimitsDaily {
    private static Map<String, Map<Integer, Integer>> lessonLimits;

    public static void setLessonLimits(Map<String, Map<Integer, Integer>> limits) {
        lessonLimits = limits;
    }

    public static int getLessonLimit(String grade, int dayOfTheWeek){
        return lessonLimits.get(grade).get(dayOfTheWeek);
    }
}
