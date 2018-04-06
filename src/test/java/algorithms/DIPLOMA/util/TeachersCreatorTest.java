package algorithms.DIPLOMA.util;

import algorithms.DIPLOMA.util.creators.TeachersCreator;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TeachersCreatorTest {
    private TeachersCreator teachersCreator;

    @Test
    public void test001createTeachers() {
        ArrayList<String> xlsStrings = new ArrayList<>();
        xlsStrings.add("Russian1;1;1;1a");
        xlsStrings.add("Russian2;2-3;2;1a");
        xlsStrings.add("Russian3;4,6;3;1a");
        xlsStrings.add("Russian4;7;4;1a");

        teachersCreator = new TeachersCreator(xlsStrings);
        teachersCreator.createTeachers();

        int expectedAmountOfTeachers = 6;
        assertEquals(expectedAmountOfTeachers, teachersCreator.getTeachers().size());
    }

    @Test
    public void test002getTeachersForDay() {
        int expectedAmountOfLessons = 1;
        assertEquals(expectedAmountOfLessons, teachersCreator.getTeachersForDay(1).size());
    }

    @Test
    public void test003getAllLessons() {
        String rangeOfLessons = "1,3-5,7-9";
        ArrayList<Integer> lessons = new TeachersCreator().getNumbersFromString(rangeOfLessons);

        int expectedAmountOfLessons = 7;
        assertEquals(expectedAmountOfLessons, lessons.size());
    }

    @Test
    public void getGradesFromString() {
        teachersCreator = new TeachersCreator();

        String grade1 = "11a";
        assertEquals(grade1, teachersCreator.getGradesFromString(grade1).get(0));

        grade1 = "9a-11a";
        String expected1 = "9a";
        String expected2 = "10a";
        String expected3 = "11a";

        ArrayList<String> actualResults = teachersCreator.getGradesFromString(grade1);
        assertEquals(expected1, actualResults.get(0));
        assertEquals(expected2, actualResults.get(1));
        assertEquals(expected3, actualResults.get(2));

    }
}