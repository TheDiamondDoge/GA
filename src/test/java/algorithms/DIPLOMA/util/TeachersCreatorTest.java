package algorithms.DIPLOMA.util;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TeachersCreatorTest {

    @Test
    public void getAllLessons() {
        String rangeOfLessons = "1,3-5,7-9";
        ArrayList<Integer> lessons = new TeachersCreator().getAllLessons(rangeOfLessons);

        int expectedAmountOfLessons = 7;
        assertEquals(expectedAmountOfLessons, lessons.size());
    }

}