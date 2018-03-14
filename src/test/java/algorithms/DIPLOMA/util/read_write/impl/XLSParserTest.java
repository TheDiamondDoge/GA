package algorithms.DIPLOMA.util.read_write.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class XLSParserTest {

    @Test
    public void getAllLessons() {
        XLSParser xlsParser = new XLSParser();
        String INPUT = "1,3-5,7-9";

        assertEquals(xlsParser.getAllLessons(INPUT).size(), 7);
    }

    @Test
    public void parse(){
        XLSParser xlsParser = new XLSParser();
        xlsParser.parse();
    }
}