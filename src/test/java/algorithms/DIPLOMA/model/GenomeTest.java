package algorithms.DIPLOMA.model;

import algorithms.DIPLOMA.data.GradeDataObject;
import algorithms.DIPLOMA.data.LessonLimitsWeekly;
import algorithms.DIPLOMA.util.creators.LimitsCreatorWeekly;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class GenomeTest {
    private Map<String, Map<String, Integer>> weeklyLimits;
    private ArrayList<Teacher> day;

    @Before
    public void setUp(){
        //GradeDataObject.GRADE = "5а";
        Map<String, Integer> tempMap = new HashMap<>();

        tempMap.put("Russian", 1);
        tempMap.put("Math", 1);
        tempMap.put("Chemistry", 1);
        tempMap.put("Phys", 1);
        tempMap.put("Litr", 1);

        weeklyLimits = new HashMap<>();
        weeklyLimits.put(GradeDataObject.GRADE, tempMap);

        day = new ArrayList<>();
        day.add(new Teacher("name", "Russian", 1, 1, "5б"));
        day.add(new Teacher("name", "Math", 2, 1,  "6а"));
        day.add(new Teacher("name", "Chemistry", 3, 1, "6а"));
        day.add(new Teacher("name", "Phys", 4, 1, "6а"));
        day.add(new Teacher("name", "Litr", 5, 1, "6а"));
    }

    @Test
    public void calcFitness() {
        LimitsCreatorWeekly limitsCreatorWeekly = mock(LimitsCreatorWeekly.class);
        when(limitsCreatorWeekly.createLimitsFromList()).thenReturn(weeklyLimits);
        LessonLimitsWeekly.setLessonLimitsWeekly(limitsCreatorWeekly.createLimitsFromList());

        Genome genome = new Genome(day);

        int expectedFitness = 0;
        assertEquals(expectedFitness, genome.getFitness());
    }
}