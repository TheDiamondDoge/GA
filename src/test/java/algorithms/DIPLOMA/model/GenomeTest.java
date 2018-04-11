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
    private ArrayList<Teacher> incorrectDay;
    private ArrayList<Teacher> correctDay;


    @Before
    public void setUp(){
        GradeDataObject.GRADE = "10б";
        Map<String, Integer> tempMap = new HashMap<>();

        tempMap.put("Russian", 1);
        tempMap.put("Math", 1);
        tempMap.put("Chemistry", 1);
        tempMap.put("Phys", 1);
        tempMap.put("Litr", 1);

        weeklyLimits = new HashMap<>();
        weeklyLimits.put(GradeDataObject.GRADE, tempMap);
    }

    @Test
    public void calcFitness() {
        initForCalcFitness();
        LimitsCreatorWeekly limitsCreatorWeekly = mock(LimitsCreatorWeekly.class);
        when(limitsCreatorWeekly.createLimitsFromList()).thenReturn(weeklyLimits);
        LessonLimitsWeekly.setLessonLimitsWeekly(limitsCreatorWeekly.createLimitsFromList());

        Genome notSuitable = new Genome(incorrectDay);

        int expectedFitness = 0;
        assertNotEquals("Genome do not matches to all criteria",expectedFitness, notSuitable.getFitness());

        Genome suitable = new Genome(correctDay);
        assertEquals("Genome matches to all criteria", expectedFitness, suitable.getFitness());
    }

    private void initForCalcFitness(){
        incorrectDay = new ArrayList<>();
        incorrectDay.add(new Teacher("nam5e", "Russian", 1, 1, "11а"));
        incorrectDay.add(new Teacher("nam4e", "Math", 2, 1,  "10б"));
        incorrectDay.add(new Teacher("na3me", "Chemistry", 3, 1, "11а"));
        incorrectDay.add(new Teacher("n2ame", "Phys", 4, 1, "10б"));
        incorrectDay.add(new Teacher("1name", "Litr", 5, 1, "10б"));

        correctDay = new ArrayList<>();
        correctDay.add(new Teacher("name2", "Russian", 1, 1, "10б"));
        correctDay.add(new Teacher("name1", "Math", 2, 1,  "10б"));
        correctDay.add(new Teacher("name3", "Chemistry", 3, 1, "10б"));
        correctDay.add(new Teacher("name4", "Phys", 4, 1, "10б"));
        correctDay.add(new Teacher("name5", "Litr", 5, 1, "10б"));
    }

    @Test
    public void weeklyLimitsInfluence() {
        LimitsCreatorWeekly limitsCreatorWeekly = mock(LimitsCreatorWeekly.class);
        when(limitsCreatorWeekly.createLimitsFromList()).thenReturn(weeklyLimits);
        LessonLimitsWeekly.setLessonLimitsWeekly(limitsCreatorWeekly.createLimitsFromList());

        Genome genome = new Genome(initForWeeklyLimitsInfluence());

        int expectedInfluence = 1;
        assertEquals("Weekly limit for subject Russian exceeded by 1", expectedInfluence, genome.weeklyLimitsInfluence());

    }

    private ArrayList<Teacher> initForWeeklyLimitsInfluence(){
        ArrayList<Teacher> day = new ArrayList<>();
        day.add(new Teacher("nam5e", "Russian", 1, 1, "10б"));
        day.add(new Teacher("nam5e", "Math", 1, 1, "10б"));
        day.add(new Teacher("nam4e", "Russian", 2, 1,  "10б"));
        return day;
    }
}