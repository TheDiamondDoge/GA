package algorithms.DIPLOMA.util.read_write.impl;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class XLSParserTest {
    private File inputFile;
    private XLSParser xlsParser;

    @Before
    public void setUp(){
        ClassLoader classLoader = getClass().getClassLoader();
        inputFile = new File(classLoader.getResource("read_write/xlsx/inputFile.xlsx").getPath().replaceAll("%20", " "));
        xlsParser = new XLSParser(inputFile);
    }

    @Test
    public void parse(){
        ArrayList<String> parseResult;
        parseResult = xlsParser.parse();

        int expectedAmountOfStrings = 6;
        assertEquals(expectedAmountOfStrings, parseResult.size());
    }
}