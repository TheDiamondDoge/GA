package algorithms.DIPLOMA.util.read_write.impl;

import algorithms.DIPLOMA.util.TeachersCreator;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class XLSParserTest {

    @Before
    public void setUp(){
        ClassLoader classLoader = getClass().getClassLoader();
    }

    @Test
    public void getAllLessons() {
        XLSParser xlsParser = new XLSParser();
        String rangeOfLessons = "1,3-5,7-9";

        ArrayList<Integer> lessons = new TeachersCreator().getAllLessons(rangeOfLessons);
        int expectedAmountOfLessons = 7;

        assertEquals(expectedAmountOfLessons, lessons.size());
    }

    @Ignore
    @Test
    public void parse(){
        XLSParser xlsParser = new XLSParser();
        xlsParser.parse();
    }
}