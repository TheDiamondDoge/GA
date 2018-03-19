package algorithms.DIPLOMA.util;

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
        xlsStrings.add("Russian1;1;1;1");
        xlsStrings.add("Russian2;2-3;2;1");
        xlsStrings.add("Russian3;4,6;3;1");
        xlsStrings.add("Russian4;7;4;1");

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
}