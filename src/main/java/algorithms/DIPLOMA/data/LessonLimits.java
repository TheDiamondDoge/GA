package algorithms.DIPLOMA.data;

import algorithms.DIPLOMA.util.creators.LimitsCreator;

import java.util.Map;

public class LessonLimits {
    private static Map<String, Integer> lessonLimits;

    static {
        LimitsCreator limitsCreator = new LimitsCreator();
        lessonLimits = limitsCreator.createLimitsFromList();
    }

    public static int getLessonLimit(String grade){
        return lessonLimits.get(grade);
    }
}
